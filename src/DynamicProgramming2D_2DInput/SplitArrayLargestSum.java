package DynamicProgramming2D_2DInput;

import java.util.Arrays;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 410，在BinarySearch中有该题的另一解，时间复杂度更低
 * @date 2022/10/19 17:22
 */
public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            //求前缀和，不开辟新的空间，而是使用原有数组
            nums[i] += nums[i -1];
        }
        int[][] dp = new int[n][m + 1]; //dp[i][j]：将前i+1个元素分成j份
        //先将数组所有位置设置为最大值，方便随后遍历更新
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        //前i+1个元素分为j份（故j <= m && j <= i + 1），相当于前k+1个元素分为j-1份（故j-2<=k<i），第k+2~i+1个元素分为一份
        //求得当前分组中值最大的一个分组的值作为dp[i][j]的一个可能值，然后所有结果中最小的赋给dp[i][j]
        for (int i = 0; i < n; i++) {
            dp[i][1] = nums[i]; //前i+1个元素分为1份即前i+1个元素求和
            for (int j = 2; j <= m && j <= i + 1; j++) {
                for (int k = j - 2; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], nums[i] - nums[k]));
                }
            }
        }
        //返回结果，将前n个元素分为m份
        return dp[n - 1][m];
    }

    public static void main(String[] args) {
        int[] nums = {7,2,5,10,8};
        int m = 2;
        int result = new SplitArrayLargestSum().splitArray(nums, m);
        System.out.println(result);
    }
}
