package BestFirstSearch;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 778
 * @date 2022/10/3 17:47
 */
public class SwimInRisingWater {
    int[] directions = {-1, 0, 1, 0, -1};
    public int swimInWater(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        boolean[][] visit = new boolean[row][col];
        int result = 0;
        //Dijkstra
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));   //按照路径上的最大值最小排序
        queue.offer(new int[]{0, 0, grid[0][0]});
        while (!queue.isEmpty()){
            int[] curr = queue.poll();
            //到达终点，返回路径上的最大值
            if (curr[0] == row - 1 && curr[1] == col - 1){
                return Math.max(result, curr[2]);
            }
            //每次元素出堆更新路径上的最大值
            result = Math.max(result, curr[2]);
            //检查并更新当前点周围的四个节点
            for (int i = 0; i < 4; i++) {
                int x = curr[0] + directions[i];
                int y = curr[1] + directions[i + 1];
                if (x >= 0 && x < row && y >= 0 && y < col && !visit[x][y]){
                    queue.offer(new int[]{x, y, grid[x][y]});
                    visit[x][y] = true;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[][] grid = {{0,1,2,3,4},{24,23,22,21,5},{12,13,14,15,16},{11,17,18,19,20},{10,9,8,7,6}};
        int result = new SwimInRisingWater().swimInWater(grid);
        System.out.println(result);
    }
}
