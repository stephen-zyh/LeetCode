package Graph_BreadthFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 934
 * @date 2022/9/3 9:22
 */
public class ShortestBridge {
    Queue<int[]> queue = new LinkedList<>();
    int[] dirs = {-1,0,1,0,-1};
    public int shortestBridge(int[][] grid) {
        //loop找出第一片陆地的位置
        loop:
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1){
                    findBoundary(i, j, grid);   //使用DFS将第一个岛的每一块陆地标记为1
                    break loop;
                }
            }
        }
        int result = 0;
        //BFS
        while (!queue.isEmpty()){
            result++;   //每向外推一圈，result加1
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] currPoint = queue.poll();
                grid[currPoint[0]][currPoint[1]] = 2;
                for (int j = 0; j < 4; j++) {
                    int x = currPoint[0] + dirs[j];
                    int y = currPoint[1] + dirs[j + 1];
                    if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] != 2){
                        if (grid[x][y] == 1){
                            return result;  //扩展到了另一个小岛（连上了桥），直接返回
                        }
                        //否则小岛边缘继续扩展
                        queue.offer(new int[]{x, y});
                        grid[x][y] = 2;
                    }
                }
            }

        }
        return result;
    }
    private void findBoundary(int abscissa, int ordinate, int[][] grid){
        //点在矩阵外或者已经被标记过，直接返回
        if (abscissa < 0 || abscissa >= grid.length || ordinate < 0 || ordinate >= grid[0].length || grid[abscissa][ordinate] == 2){
            return;
        }
        //离第一个小岛最近的一圈海洋入队
        if (grid[abscissa][ordinate] == 0){
            queue.offer(new int[]{abscissa, ordinate});
            return;
        }
        grid[abscissa][ordinate] = 2;   //标记还未被标记的陆地
        //递归调用findBoundary()，检查当前节点周围四个节点
        for (int i = 0; i < 4; i++) {
            int x = abscissa + dirs[i];
            int y = ordinate + dirs[i + 1];
            findBoundary(x, y, grid);
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{1,1,1,1,1},{1,0,0,0,1},{1,0,1,0,1},{1,0,0,0,1},{1,1,1,1,1}};
//        int[][] grid = {{0,0,0,0,0,0,0},{1,0,0,1,0,0,0},{1,1,1,1,0,0,0},{0,0,0,1,0,0,0},{1,0,0,0,0,0,0},{1,1,0,0,0,0,0},{1,0,0,0,0,0,0}};
        int result = new ShortestBridge().shortestBridge(grid);
        System.out.println(result);
    }
}
