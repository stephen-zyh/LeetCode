package BestFirstSearch;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 505
 * @date 2022/10/3 10:36
 */
public class TheMazeII {
    int[] directions = {-1, 0, 1, 0, -1};
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 || maze[0].length == 0){
            return -1;
        }
        int row = maze.length, col = maze[0].length;
        boolean[][] visit = new boolean[row][col];
        //Dijkstra
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));   //按走的步数排序
        queue.offer(new int[]{start[0], start[1], 0});
        while (!queue.isEmpty()){
            int[] curr = queue.poll();
            if (visit[curr[0]][curr[1]]){
                continue;
            }
            visit[curr[0]][curr[1]] = true;
            if (curr[0] == destination[0] && curr[1] == destination[1]){
                return curr[2];
            }
            //沿着每一个方向走直到遇到墙，在此过程中，到达同一位置的，距离更短的排在更靠近堆的位置
            for (int i = 0; i < 4; i++) {
                int x = curr[0];
                int y = curr[1];
                int steps = 0;
                while (x + directions[i] >= 0 && x + directions[i] < row && y + directions[i + 1] >= 0
                        && y + directions[i + 1] < col && maze[x + directions[i]][y + directions[i + 1]] == 0){
                    x += directions[i];
                    y += directions[i + 1];
                    steps++;
                }
                queue.offer(new int[]{x, y, curr[2] + steps});
            }
        }
        //到不了目的位置，返回-1
        return -1;
    }

    public static void main(String[] args) {
        int[][] maze = {{0, 0, 1, 0, 0},
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 1, 0},
                        {1, 1, 0, 1, 1},
                        {0, 0, 0, 0, 0}};
        int[] start = {0, 4}, destination = {4, 4};
        int result = new TheMazeII().shortestDistance(maze, start, destination);
        System.out.println(result);
    }
}
