package DynamicProgramming_Knapsack;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 377
 * @date 2022/10/28 10:27
 */
public class CombinationSumIV {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1]; //dp[i]：构成和为i的序列的总数
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i - num >= 0) {
                    //i由i-num加上num得来（即dp[i]的状态由dp[i-num]转移而来），故dp[i]应当是所有dp[i-num]之和
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        int target = 4;
        int result = new CombinationSumIV().combinationSum4(nums, target);
        System.out.println(result);
    }
}
