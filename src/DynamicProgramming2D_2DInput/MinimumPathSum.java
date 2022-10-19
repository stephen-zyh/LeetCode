package DynamicProgramming2D_2DInput;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 64
 * @date 2022/10/18 19:20
 */
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = grid[0][0];
        //更新第0行和第0列的代价
        for (int i = 1; i < row; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }
        for (int j = 1; j < col; j++) {
            dp[0][j] = grid[0][j] + dp[0][j - 1];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                //到达本节点的最小代价等于本节点的费用加上到达该节点上方或者左方的最小代价
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j -1]) + grid[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    public static void main(String[] args) {
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        int result = new MinimumPathSum().minPathSum(grid);
        System.out.println(result);
    }
}
