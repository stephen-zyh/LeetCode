package DepthFirstSearch;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 490
 * @date 2022/10/5 12:15
 */
public class TheMaze {
    int[] directions = {-1, 0, 1, 0, -1};
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visit = new boolean[maze.length][maze[0].length];
        visit[start[0]][start[1]] = true;
        return canFindPath(maze, start, destination, visit);
    }

    private boolean canFindPath(int[][] maze, int[] curr, int[] destination, boolean[][] visit){
        //到达终点，返回true
        if (curr[0] == destination[0] && curr[1] == destination[1]){
            return true;
        }
        //更新当前点四周的点
        for (int i = 0; i < 4; i++) {
            int x = curr[0];
            int y = curr[1];
            //不撞南墙不回头
            while (x + directions[i] >= 0 && x + directions[i] < maze.length && y + directions[i + 1] >= 0
                    && y + directions[i + 1] < maze[0].length && maze[x + directions[i]][y + directions[i + 1]] == 0){
                x += directions[i];
                y += directions[i + 1];
            }
            // DFS，对于每一个方向上的这样的节点，继续寻找是否有到达终点的路径，如果有则返回true
            if (!visit[x][y]){
                visit[x][y] = true; //修改访问状态
                boolean flag = canFindPath(maze, new int[]{x, y}, destination, visit);
                if (flag){
                    return true;
                }
            }
        }
        //四个方向上都没有可行的路径
        return false;
    }

    public static void main(String[] args) {
        int[][] maze = {{0, 0, 1, 0, 0},
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 1, 0},
                        {1, 1, 0, 1, 1},
                        {0, 0, 0, 0, 0}};
        int[] start = {0, 4}, destination = {4, 4};
        boolean result = new TheMaze().hasPath(maze, start, destination);
        System.out.println(result);
    }
}
