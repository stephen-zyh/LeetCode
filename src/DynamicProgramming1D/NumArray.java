package DynamicProgramming1D;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 303，相当于求前缀和
 * @date 2022/10/11 18:41
 */
public class NumArray {
    private int[] dp;
    public NumArray(int[] nums) {
        if (nums == null || nums.length == 0){
            return;
        }
        dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i - 1] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        if (left == 0){
            return dp[right];
        }
        return dp[right] - dp[left - 1];
    }

    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(numArray.sumRange(0, 2)); // return 1 ((-2) + 0 + 3)
        System.out.println(numArray.sumRange(2, 5)); // return -1 (3 + (-5) + 2 + (-1))
        System.out.println(numArray.sumRange(0, 5)); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
    }
}
