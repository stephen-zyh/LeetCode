package DynamicProgramming2D_1DInputPlusK;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 123
 * @date 2022/10/27 18:57
 */
public class BestTimeToBuyAndSellStockIII {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        //dp[i][0]：第i天第一次持有股票状态的收益；dp[i][1]：第i天第一次出售股票状态的收益
        //dp[i][0]：第i天第二次持有股票状态的收益；dp[i][1]：第i天第二次出售股票状态的收益
        int[][] dp = new int[len][4];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = -prices[0];  //第0天第二次持有股票，相当于少使用一次交易机会
        dp[0][3] = 0;
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(-prices[i], dp[i - 1][0]);  //状态由前一天已经是第一次持有股票的状态和今天才开始第一次持有股票转移而来
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);    //与dp[i][0]同理
            dp[i][2] = Math.max(dp[i - 1][1] - prices[i], dp[i - 1][2]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] + prices[i]);
        }
        return dp[len - 1][3];
    }

    //上述方法的改进版，进行了状态压缩，将空间复杂度降低到O(1)
    public int maxProfit1(int[] prices) {
        int len = prices.length;
        int sellTwice = 0;
        int holdTwice = -prices[0];
        int sellOnce = 0;
        int holdOnce = -prices[0];
        for (int i = 1; i < len; i++) {
            sellTwice = Math.max(sellTwice, holdTwice + prices[i]);
            holdTwice = Math.max(sellOnce - prices[i], holdTwice);
            sellOnce = Math.max(sellOnce, holdOnce + prices[i]);
            holdOnce = Math.max(-prices[i], holdOnce);
        }
        return sellTwice;
    }

    public static void main(String[] args) {
        int[] prices = {2,1,4,5,2,9,7};
        int result = new BestTimeToBuyAndSellStockIII().maxProfit(prices);
        System.out.println(result);
    }
}
