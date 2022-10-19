package DynamicProgramming2D_2DInput;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 63，leetcode 62的相似题
 * @date 2022/10/18 21:15
 */
public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            //到达第0列的各个位置只有一种走法，即一直往下走，但遇到障碍后，之后的各个位置都不能再通行
            if (obstacleGrid[i][0] == 1)    break;
            dp[i][0] = 1;
        }
        for (int j = 0; j < col; j++) {
            //到达第0行的各个位置只有一种走法，即一直往右走，但遇到障碍后，之后的各个位置都不能再通行
            if (obstacleGrid[0][j] == 1)    break;
            dp[0][j] = 1;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                //该节点有障碍物不能通行
                if (obstacleGrid[i][j] == 1){
                    continue;
                }
                //到某一节点的走法等于从该节点上方到达该节点与从该节点左方到达该节点的走法数之和
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[row - 1][col - 1];
    }

    public static void main(String[] args) {
        int[][] obstacleGrid = {{0,0,0},{0,1,0},{0,0,0}};
        int result = new UniquePathsII().uniquePathsWithObstacles(obstacleGrid);
        System.out.println(result);
    }
}
