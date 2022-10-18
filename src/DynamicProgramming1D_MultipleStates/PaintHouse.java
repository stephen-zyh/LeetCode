package DynamicProgramming1D_MultipleStates;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 256
 * @date 2022/10/17 8:55
 */
public class PaintHouse {
    public int minCost(int[][] costs) {
        int len = costs.length;
        int[][] dp = new int[len][3];   //dp[i][j]：第i个房子涂成第j种颜色
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
        for (int i = 1; i < len; i++) {
            //第i个房子涂成第j种颜色的费用，应该是前一个房子涂成不是第j种颜色的最小费用加上途第i个房子的作用
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
        }
        //结果取最小值
        return Math.min(dp[len - 1][0], Math.min(dp[len - 1][1], dp[len - 1][2]));
    }

    public static void main(String[] args) {
        int[][] costs = {{17,2,17},{16,16,5},{14,3,19}};
        int result = new PaintHouse().minCost(costs);
        System.out.println(result);
    }
}
