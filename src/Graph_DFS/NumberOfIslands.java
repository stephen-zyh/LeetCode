package Graph_DFS;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 200
 * @date 2022/9/9 13:58
 */
public class NumberOfIslands {
    int[] directions = {-1, 0, 1, 0, -1};
    public int numIslands(char[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                //grid[i][j] == '1'表示遇到了陆地，DFS将该岛的所有陆地重新标记为'2'，并将结果加1
                if (grid[i][j] == '1'){
                    markIsland(i, j, grid);
                    result++;
                }
            }
        }
        return result;
    }

    private void markIsland(int abscissa, int ordinate, char[][] grids){
        grids[abscissa][ordinate] = '2';
        for (int i = 0; i < 4; i++) {
            int x = abscissa + directions[i];
            int y = ordinate + directions[i + 1];
            //如果当前点四周的点在矩阵中且是未标记的陆地，对其进行标记
            if (x >= 0 && x < grids.length && y >= 0 && y <grids[0].length && grids[x][y] == '1') {
                markIsland(x, y, grids);
            }
        }
    }

    public static void main(String[] args) {
        char[][] grids = {
        {'1','1','1','1','0'},
        {'1','1','0','1','0'},
        {'1','1','0','0','0'},
        {'0','0','0','0','0'}};
        int result = new NumberOfIslands().numIslands(grids);
        System.out.println(result);
    }
}
