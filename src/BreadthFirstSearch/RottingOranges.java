package BreadthFirstSearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 994
 * @date 2022/9/26 16:01
 */
public class RottingOranges {
    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int result = -1;
        int[] directions = {-1, 0, 1, 0, -1};
        //将腐烂的橘子坐标入队
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2){
                    queue.offer(new int[]{i, j});
                }
            }
        }
        //没有腐烂橘子但是有新鲜橘子存在时，橘子永远不会腐烂，返回-1；没有橘子则直接返回0
        if (queue.isEmpty()){
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1){
                        return -1;
                    }
                }
            }
            return 0;
        }
        //BFS，从已腐烂的橘子向外层逐层扩散，直至没有橘子可以继续腐烂
        while (!queue.isEmpty()){
            result++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] currPoint = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int x = currPoint[0] + directions[j];
                    int y = currPoint[1] + directions[j + 1];
                    //四周的点在矩阵内的、是新鲜橘子的才会腐烂，这些点作为最外层腐烂橘子加入到队列中去
                    if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1){
                        grid[x][y] = 2;
                        queue.offer(new int[]{x, y});
                    }
                }
            }
        }
        //检查是否还有橘子未腐烂
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1){
                    return -1;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
//        int[][] grid = {{2,1,1},{0,1,1},{1,0,1}};
        int[][] grid = {{1,2}};
        int result = new RottingOranges().orangesRotting(grid);
        System.out.println(result);
    }
}
