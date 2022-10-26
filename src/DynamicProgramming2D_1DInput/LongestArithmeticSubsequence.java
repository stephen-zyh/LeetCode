package DynamicProgramming2D_1DInput;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1027
 * @date 2022/10/25 18:56
 */
public class LongestArithmeticSubsequence {
    public int longestArithSeqLength(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][1001];    //dp[j][d]：结尾为j且公差为d的等差子序列长度
        int maxLength = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int d = nums[j] - nums[i] + 500;    //加500的偏移量，保证d为正，不会下标越界
                dp[j][d] = dp[i][d] + 1;
                maxLength = Math.max(maxLength, dp[j][d]);
            }
        }
        return maxLength + 1;
    }

    public static void main(String[] args) {
        int[] nums = {20,1,15,3,10,5,8};
        int result = new LongestArithmeticSubsequence().longestArithSeqLength(nums);
        System.out.println(result);
    }
}
