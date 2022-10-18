package DynamicProgramming1D_MultipleStates;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 801
 * @date 2022/10/17 18:09
 */
public class MinimumSwapsToMakeSequencesIncreasing {
    public int minSwap(int[] nums1, int[] nums2) {
        int len = nums1.length;
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = 1;
        /*
        条件①：nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]
        条件②：nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]
        根据是否满足两个条件，可以分为三种情况：
        一、同时满足①和②：i-1处可交换，也可不交换；i处可交换，也可不交换；二者互不影响
        二、满足条件①但不满足条件②：由于只交换一处不能满足两个序列分别单调递增，i-1处和i处要么都交换，要么都不交换
        三、不满足条件①：两处同时交换不会改变i-1到i处nums1和nums2的增减性，因此只能交换一处
         */
        for (int i = 1; i < len; i++) {
            if (nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]){
                if (nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]){
                    dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]);
                    dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + 1;
                }else {
                    dp[i][0] = dp[i - 1][0];
                    dp[i][1] = dp[i - 1][1] + 1;
                }
            }else {
                dp[i][0] = dp[i - 1][1];
                dp[i][1] = dp[i - 1][0] + 1;
            }
        }
        return Math.min(dp[len - 1][0], dp[len - 1][1]);
    }

    public static void main(String[] args) {
        int[] nums1 = {0,3,5,8,9}, nums2 = {2,1,4,6,9};
        int result = new MinimumSwapsToMakeSequencesIncreasing().minSwap(nums1, nums2);
        System.out.println(result);
    }
}
