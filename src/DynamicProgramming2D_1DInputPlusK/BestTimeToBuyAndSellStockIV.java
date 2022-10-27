package DynamicProgramming2D_1DInputPlusK;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 188，参考leetcode 123
 * @date 2022/10/27 19:58
 */
public class BestTimeToBuyAndSellStockIV {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][2 * k];
        //dp[0][2*i]和dp[0][2*i + 1]分别代表第0天第i次持有和第i次售出股票状态下的收益
        for (int i = 0; i < k; i++) {
            dp[0][2*i] = -prices[0];    //第0天第i次持有股票，相当于少使用i-1次交易机会
            dp[0][2*i + 1] = 0;
        }
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(-prices[i], dp[i - 1][0]);  //状态由前一天已经是第一次持有股票的状态和今天才开始第一次持有股票转移而来
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);    //与dp[i][0]同理
            for (int j = 1; j < k; j++) {
                int index = 2 * j;
                dp[i][index] = Math.max(dp[i - 1][index - 1] - prices[i], dp[i - 1][index]);
                dp[i][index + 1] = Math.max(dp[i - 1][index] + prices[i], dp[i - 1][index + 1]);
            }
        }
        return dp[len - 1][2*k - 1];
    }

    public static void main(String[] args) {
        int k = 2;
        int[] prices = {3,2,6,5,0,3};
        int result = new BestTimeToBuyAndSellStockIV().maxProfit(k, prices);
        System.out.println(result);
    }
}
