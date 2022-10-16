package DynamicProgramming1D;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 343，本质是找规律
 * @date 2022/10/16 18:17
 */
public class IntegerBreak {
    public int integerBreak(int n) {
        switch (n){
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 4;
            case 5:
                return 6;
            case 6:
                return 9;
            default:
                int[] dp = new int[n + 1];
                dp[2] = 1;
                dp[3] = 2;
                dp[4] = 4;
                dp[5] = 6;
                dp[6] = 9;
                //状态转移方程为dp[i] = dp[i - 3] * 3
                for (int i = 7; i <= n; i++) {
                    dp[i] = dp[i - 3] * 3;
                }
                return dp[n];
        }
    }

    public static void main(String[] args) {
        int n = 58;
        int result = new IntegerBreak().integerBreak(n);
        System.out.println(result);
    }
}
