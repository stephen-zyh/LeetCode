package DynamicProgramming1D;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 53
 * @date 2022/10/11 18:52
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int result = dp[0];
        for (int i = 1; i < nums.length; i++) {
            //若sum>0,则sum对结果增大有作用，此时让sum=sum+1;
            //若sum<=0，则sum对结果增大无作用
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        //结果应该是dp中的最大值，也可以在进行动态规划的同时对result进行更新
        for (int i = 1; i < nums.length; i++) {
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int result = new MaximumSubarray().maxSubArray(nums);
        System.out.println(result);
    }
}
