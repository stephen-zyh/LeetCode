package DynamicProgramming_Knapsack;

import java.util.Arrays;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 322
 * @date 2022/10/28 10:01
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1]; //dp[i]：总金额为i时的最小硬币数
        Arrays.fill(dp, 0x3f3f3f3f);    //统一初始化为无限大，方便动态规划中逐渐减小dp[i]的值（如果设置为Integer.MAX_VALUE，加1就会溢出）
        Arrays.sort(coins);
        dp[0] = 0;  //总金额为0不需要任何硬币
        for (int i = coins[0]; i <= amount; i++) {
            for (int j = 0; j < coins.length && i - coins[j] >= 0; j++) {
                //比较选用这枚硬币和不选用这枚硬币来凑出总金额i，选择其中使用硬币少的一个
                dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }
        //如果dp[amount]仍是初始值，说明凑不出amount，返回-1，否则返回dp[amount]
        return dp[amount] == 0x3f3f3f3f ? -1: dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        int result = new CoinChange().coinChange(coins, amount);
        System.out.println(result);
    }
}
