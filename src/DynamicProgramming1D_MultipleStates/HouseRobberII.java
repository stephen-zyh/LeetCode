package DynamicProgramming1D_MultipleStates;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 213
 * @date 2022/10/17 10:50
 */
public class HouseRobberII {
    //既然 0 和 len-1 不能同时偷，那么拆成计算偷1 ~ len-2的最大收益和计算偷2 ~ len-1的最大收益，两者取最大值
    public int rob(int[] nums) {
        int len = nums.length;
        //len == 1是特殊情况，偷他不会引起警报
        if(len == 1){
            return nums[0];
        }
        if(len == 2){
            return Math.max(nums[0], nums[1]);
        }
        int result;
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        //计算偷1 ~ len-2的最大收益
        for (int i = 1; i < len - 1; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);    //不偷这一家，上一家可以偷了，也可以没偷，取收益的最大值
            dp[i][1] = dp[i - 1][0] + nums[i];  //要偷这一家，上一家不能偷，总收益是不偷上一家的总收益加上偷这一家的收益
        }
        //更新最大值
        result = Math.max(dp[len - 2][0], dp[len - 2][1]);
        //不重新开辟空间，而是对dp的值进行重置，减少空间的使用
        dp[1][0] = 0;
        dp[1][1] = nums[1];
        //计算偷2 ~ len-1的最大收益
        for (int i = 2; i < len; i++) {
            //与计算偷1 ~ len-2的最大收益时同理
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        result = Math.max(result, Math.max(dp[len - 1][0], dp[len - 1][1]));
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        int result = new HouseRobberII().rob(nums);
        System.out.println(result);
    }
}
