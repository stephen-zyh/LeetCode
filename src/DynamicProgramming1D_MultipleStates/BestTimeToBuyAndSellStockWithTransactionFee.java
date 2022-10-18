package DynamicProgramming1D_MultipleStates;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 714
 * @date 2022/10/17 10:22
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {
    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        int[][] dp = new int[len][2];   //dp[i][0]表示不持有股票，dp[i][1]表示持有股票
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            //不持有股票分为前一天就不持有股票和前一天持有股票但今天抛出两种情况
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            //持有股票分为前一天就已经持有股票和前一天不持有股票但今天买入两种情况
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return Math.max(dp[len - 1][0], dp[len - 1][1]);
    }

    public static void main(String[] args) {
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        int result = new BestTimeToBuyAndSellStockWithTransactionFee().maxProfit(prices, fee);
        System.out.println(result);
    }
}
