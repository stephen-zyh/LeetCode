package DynamicProgramming1D_MultipleStates;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 978
 * @date 2022/10/17 18:52
 */
public class LongestTurbulentSubarray {
    public int maxTurbulenceSize(int[] arr) {
        int len = arr.length;
        int result = 1;
        int[][] dp = new int[len][2];   //dp[i][0]：以i结尾的i-1到i递减的湍流子数组长度，dp[i][1]反之
        dp[0][0] = 1;
        dp[0][1] = 1;
        for (int i = 1; i < len; i++) {
            dp[i][0] = 1;
            dp[i][1] = 1;
            if (arr[i- 1] > arr[i]){
                dp[i][0] = dp[i - 1][1] + 1;
            }else if (arr[i - 1] < arr[i]){
                dp[i][1] = dp[i - 1][0] + 1;
            }
            //结果应该是原有结果与当前两种湍流子数组长度取最大值
            result = Math.max(result, Math.max(dp[i][0], dp[i][1]));
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {9,4,2,10,7,8,8,1,9};
        int result = new LongestTurbulentSubarray().maxTurbulenceSize(arr);
        System.out.println(result);
    }
}
