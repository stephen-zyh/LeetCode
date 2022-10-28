package DynamicProgramming_Knapsack;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 416
 * @date 2022/10/28 11:16
 */
public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        //如果sum是奇数，不可能二等分，直接返回false
        if ((sum & 1) == 1){
            return false;
        }
        sum /= 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        //注意：nums的循环在外层，这保证了nums中的每一个元素在求和过程中只使用一次
        for (int num : nums){
            //从后往前遍历，保证在dp[j]基础上添加num不会影响到dp[i + num]是否添加num（即防止同一num添加多次）
            for (int i = sum; i >= 0; i--) {
                if (i >= num){
                    //只要所有方案中有一个和为i即视为成功
                    dp[i] = dp[i] || dp[i - num];
                }
            }
        }
        return dp[sum];
    }

    public static void main(String[] args) {
        int[] nums = {1,5,11,5};
        boolean result = new PartitionEqualSubsetSum().canPartition(nums);
        System.out.println(result);
    }
}
