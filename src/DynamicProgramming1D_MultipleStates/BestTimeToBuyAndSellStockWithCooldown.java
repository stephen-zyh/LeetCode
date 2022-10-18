package DynamicProgramming1D_MultipleStates;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 309
 * @date 2022/10/17 9:12
 */
public class BestTimeToBuyAndSellStockWithCooldown {
    //参考https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/solution/mai-mai-gu-piao-xi-lie-by-my_mel-mz32/
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][2];   //dp[i][0]表示不持有股票，dp[i][1]表示持有股票
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            //不持有股票分为前一天就不持有股票和前一天持有股票但今天抛出两种情况
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            //持有股票分为前一天就已经持有股票和前两天不持有股票（一天前是冷冻期）但今天买入两种情况
            //判断是否i - 2 < 0是为了区别第一次购入股票（不需要冷冻期）和不是第一次买入股票
            dp[i][1] = Math.max(dp[i - 1][1], ((i - 2 < 0 ? 0 : dp[i - 2][0]) - prices[i]));
        }
        return Math.max(dp[len - 1][0], dp[len - 1][1]);
    }

    public static void main(String[] args) {
        int[] prices = {1,2,4};
        int result = new BestTimeToBuyAndSellStockWithCooldown().maxProfit(prices);
        System.out.println(result);
    }
}
