package DynamicProgramming1D;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 198
 * @date 2022/10/11 20:18
 */
public class HouseRobber {
    public int rob(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][2];
        //dp[i][0]: 不偷当前这家；dp[i][1]: 偷当前这家
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);    //不偷当前这家，前一家可以偷也可以不偷，取二者的大值
            dp[i][1] = dp[i - 1][0] + nums[i];  //要偷当前这一家，则不能偷前一家
        }
        return Math.max(dp[len - 1][0], dp[len - 1][1]);
    }

    public static void main(String[] args) {
        int[] nums = {2,7,9,3,1};
        int result = new HouseRobber().rob(nums);
        System.out.println(result);
    }
}
