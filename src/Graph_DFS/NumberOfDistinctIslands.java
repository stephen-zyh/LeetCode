package Graph_DFS;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 694
 * @date 2022/9/11 9:47
 */
public class NumberOfDistinctIslands {
    public int numDistinctIslands(int[][] grid) {
        Set<String> set = new HashSet<>();  //set记录遍历岛屿的不同路径数目
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0){
                    StringBuilder builder = new StringBuilder();
                    recordPath(i, j, grid, builder);
                    set.add(builder.toString());
                }
            }
        }
        return set.size();
    }
    private void recordPath(int abscissa, int ordinate, int[][] grid, StringBuilder builder){
        //base case
        //当出现以下三种情况时停止递归：1.该点不在矩阵内；2.该点不是陆地（值为0）；3.该点已经被遍历过（遍历后值由1改为了0）
        if (abscissa < 0 || abscissa >= grid.length || ordinate < 0 || ordinate >= grid[0].length
                || grid[abscissa][ordinate] == 0){
            return;
        }
        grid[abscissa][ordinate] = 0;   //将该点的值置0
        //遍历当前点四周的点，并记录路径
        recordPath(abscissa - 1, ordinate, grid, builder.append('l'));
        recordPath(abscissa, ordinate - 1, grid, builder.append('u'));
        recordPath(abscissa + 1, ordinate, grid, builder.append('r'));
        recordPath(abscissa, ordinate + 1, grid, builder.append('d'));
    }

    public static void main(String[] args) {
//        int[][] grid = {{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        int[][] grid = {{1,1,0,1,1},{1,0,0,0,0},{0,0,0,0,1},{1,1,0,1,1}};
        int result = new NumberOfDistinctIslands().numDistinctIslands(grid);
        System.out.println(result);
    }
}
