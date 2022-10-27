package DynamicProgramming2D_1DInputPlusK;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 265
 * @date 2022/10/27 10:55
 */
public class PaintHouseII {
    public int minCostII(int[][] costs) {
        int n = costs.length;
        int k = costs[0].length;
        int[][] dp = new int[n][k]; //dp[i][j]：第i座房子涂成第j种颜色所需的最低费用
        for (int i = 0; i < k; i++) {
            dp[0][i] = costs[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                int min = Integer.MAX_VALUE;
                for (int l = 0; l < k; l++) {
                    //当前房子涂成的颜色不能与上一个房子相同
                    if (l != j){
                        min = Math.min(min, dp[i - 1][l]);
                    }
                }
                dp[i][j] = min + costs[i][j];
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            result = Math.min(result, dp[n - 1][i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] costs = {{1,5,3},{2,9,4}};
        int result = new PaintHouseII().minCostII(costs);
        System.out.println(result);
    }
}
