package DynamicProgramming1D;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 279
 * @date 2022/10/13 19:28
 */
public class PerfectSquares {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = i;  //初始化，最大为i，即由i个1组成
            for (int j = 1; i - j * j >= 0; j++) {
                //dp[i]是与其相差j^2的k的dp[k] + 1的最小值
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 12;
        int result = new PerfectSquares().numSquares(n);
        System.out.println(result);
    }
}
