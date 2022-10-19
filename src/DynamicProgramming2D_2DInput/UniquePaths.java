package DynamicProgramming2D_2DInput;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 62
 * @date 2022/10/18 20:50
 */
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            //到达第0列的各个位置只有一种走法，即一直往下走
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            //到达第0行的各个位置只有一种走法，即一直往右走
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                //到某一节点的走法等于从该节点上方到达该节点与从该节点左方到达该节点的走法数之和
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public int uniquePaths2(int m, int n) {
        //改进版，降低空间复杂度（实际提交发现改进效果不明显）
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        int m = 3, n = 7;
        int result = new UniquePaths().uniquePaths(m, n);
        System.out.println(result);
    }
}
