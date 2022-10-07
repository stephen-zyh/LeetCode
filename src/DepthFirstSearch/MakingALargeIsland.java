package DepthFirstSearch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 827
 * @date 2022/10/7 9:08
 */
public class MakingALargeIsland {
    int[] directions = {-1, 0, 1, 0, -1};
    Map<Integer, Integer> map = new HashMap<>();
    public int largestIsland(int[][] grid) {
        int result = 0;
        int index = 2;  //由于矩阵都是0和1组成，因此让岛屿的标记从2开始累加
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                //遇到新的岛屿，DFS对岛屿进行标记，并用map记录标记为index的岛屿的面积，
                //并将其与当前最大值比较（因为标记完整个矩阵后可能没有剩余的0）
                if (grid[i][j] == 1){
                    map.put(index, 0);
                    markIsland(grid, i, j, index);
                    result = Math.max(result, map.get(index));
                    index++;
                }
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                //改变矩阵中剩余的单个的0，看能不能构成更大的岛屿
                if (grid[i][j] == 0){
                    Set<Integer> set = new HashSet<>(); //0的周围两个以上方向可能挨着的是同一个岛屿，因此用set标记周围出现的不同岛屿
                    int area = 1;   //初始面积（将给位置0变为1，不遍历周围的面积）
                    //对该点周围进行遍历
                    for (int k = 0; k < 4; k++) {
                        int x = i + directions[k];
                        int y = j + directions[k + 1];
                        //该点四个方向上在矩阵中且是岛屿（grid[x][y] != 0）且是新的没有被计算的岛屿（set中不包含），才能将其面积加入area中
                        if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] != 0 && !set.contains(grid[x][y])){
                            area += map.get(grid[x][y]);
                            set.add(grid[x][y]);
                        }
                    }
                    //构成的新岛屿与最大值比较取大者
                    result = Math.max(area, result);
                }
            }
        }
        return result;
    }

    //DFS唯一标记不同岛屿
    private void markIsland(int[][] grid, int row, int col, int mark){
        grid[row][col] = mark;  //更新岛屿标记
        map.put(mark, map.get(mark) + 1);   //更新岛屿面积
        //遍历四周节点
        for (int i = 0; i < 4; i++) {
            int x = row + directions[i];
            int y = col + directions[i + 1];
            //四周在矩阵中且值为1（当前岛尚未被标记的部分）的点才会被标记
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1){
                markIsland(grid, x, y, mark);
            }
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 0}, {0, 1}};
        int result = new MakingALargeIsland().largestIsland(grid);
        System.out.println(result);
    }
}
