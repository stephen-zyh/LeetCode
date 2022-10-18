package DynamicProgramming1D_MultipleStates;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 790
 * @date 2022/10/17 19:34
 */
public class DominoAndTrominoTiling {
    public int numTilings(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 5;
        long[] dp = new long[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;
        for (int i = 4; i <= n; i++) {
            //要问为什么状态转移方程是dp[i] = dp[i - 1] * 2 + dp[i - 3]，我是用1~10这10个样例找出的规律，当然网上也有推导，不过挺复杂的
            dp[i] = (dp[i - 1] * 2 + dp[i - 3]) % 1000000007;
        }
        return (int) dp[n];
    }

    public static void main(String[] args) {
        int n = 1000;
        int result = new DominoAndTrominoTiling().numTilings(n);
        System.out.println(result);
    }
}
