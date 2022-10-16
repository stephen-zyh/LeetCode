package DynamicProgramming1D;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 300
 * @date 2022/10/13 11:35
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int result = 0;
        int[] dp = new int[nums.length];    //dp[i]：当前找到的长为i的递增子序列最后一个元素的最小值
        for (int num : nums){
            int left = 0, right = result;
            //二分查找
            while (left < right){
                int middle = (left + right) / 2;
                if (dp[middle] < num){
                    left = middle + 1;
                }else {
                    right = middle;
                }
            }
            dp[left] = num;
            //如果二分查找的右边界没变过，说明num比dp[right]更大，left==result+1，更新最长序列的长度
            if (result == right){
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        int result = new LongestIncreasingSubsequence().lengthOfLIS(nums);
        System.out.println(result);
    }
}
