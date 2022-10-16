package DynamicProgramming1D;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 413
 * @date 2022/10/16 9:27
 */
public class ArithmeticSlices {
    public int numberOfArithmeticSlices(int[] nums) {
        int len = nums.length;
        int result = 0;
        int[] dp = new int[len];    //dp[i]: 以位置i上的元素结尾的等差数列的数目
        for (int i = 2; i < len; i++) {
            //是等差数列
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]){
                //结尾索引是i的等差数列比起结尾索引是i-1的元素来说，能比其多组成一个最大长度比起大1的子数组
                dp[i] = dp[i - 1] + 1;
                result += dp[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        int result = new ArithmeticSlices().numberOfArithmeticSlices(nums);
        System.out.println(result);
    }
}
