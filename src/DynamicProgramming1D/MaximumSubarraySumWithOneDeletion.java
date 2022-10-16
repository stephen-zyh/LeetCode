package DynamicProgramming1D;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1186
 * @date 2022/10/16 20:59
 */
public class MaximumSubarraySumWithOneDeletion {
    public int maximumSum(int[] arr) {
        int[][] dp = new int[arr.length][2];    //dp[i][0]: 不添加当前元素；dp[i][1]: 添加当前元素
        dp[0][0] = -1000;
        dp[0][1] = arr[0];
        int result = -1000;
        for (int i = 1; i < arr.length; i++) {
            //dp[i][0]可能有两种情况，一是前面是从某一位置开始的连续数组的和，不添加当前元素；
            // 二是前面是从某一位置开始的连续数组并已删除一个元素的和，当前位置元素必须添加
            dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0] + arr[i]);
            //若sum>0,则sum对结果增大有作用，此时让sum=sum+1; 若sum<=0，则sum对结果增大无作用
            dp[i][1] = Math.max(dp[i - 1][1] + arr[i], arr[i]);
            //result取所有结果的最大值
            result = Math.max(result, Math.max(dp[i][0], dp[i][1]));
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1,-2,0,3};
        int result = new MaximumSubarraySumWithOneDeletion().maximumSum(arr);
        System.out.println(result);
    }
}
