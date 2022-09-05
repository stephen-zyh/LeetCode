package Graph_BreadthFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1162
 * @date 2022/9/2 17:15
 */
public class AsFarFromLandAsPossible {
    public int maxDistance(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        //记录陆地的坐标
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1){
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int[] finalPoint = null;    //记录结果的横纵坐标
        boolean hasOcean = false;   //一旦出现有0，则将其置为true
        int[] dirs = {-1,0,1,0,-1};
        while (!queue.isEmpty()){
            finalPoint = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = finalPoint[0] + dirs[i];
                int y = finalPoint[1] + dirs[i + 1];
                //如果该点在矩阵以外、该点不是海洋以及该点是海洋但已经被标记过了，则直接跳过；否则将该点值改为其与最近的陆地的距离加1
                if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != 0){
                    continue;
                }
                grid[x][y] = grid[finalPoint[0]][finalPoint[1]] + 1;
                hasOcean = true;
                queue.offer(new int[]{x, y});
            }
        }
        if (!hasOcean || finalPoint == null){
            return -1;
        }
        return grid[finalPoint[0]][finalPoint[1]] - 1;
    }

    public static void main(String[] args) {
        int[][] grid = {{1,0,1},{0,0,0},{1,0,1}};
        int result = new AsFarFromLandAsPossible().maxDistance(grid);
        System.out.println(result);
    }
}
