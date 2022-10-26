package DynamicProgramming2D_1DInput;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 873
 * @date 2022/10/26 9:07
 */
public class LengthOfLongestFibonacciSubsequence {
    public int lenLongestFibSubseq(int[] arr) {
        int len = arr.length;
        int[][] dp = new int[len][len]; //dp[i][j]：以下标i,j结尾的斐波那契子序列长度
        int result = 2;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            Arrays.fill(dp[i], 2);
            map.put(arr[i], i); //由于数组单调递增，故而对应位置的值和下标之间是一一对应关系，可以使搜索时间降低至O(1)
        }
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int next = arr[i] + arr[j];
                //确定斐波那契数列下一个元素是否在数组中，如果在，则修改以arr[j]和next结尾的斐波那契序列长度
                if (map.containsKey(next)){
                    int k = map.get(next);
                    dp[j][k] = Math.max(dp[j][k], dp[i][j] + 1);
                    result = Math.max(result, dp[j][k]);
                }
            }
        }
        //长度大于等于3才算做找到了斐波那契数列
        return result == 2 ? 0 : result;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8};
        int result = new LengthOfLongestFibonacciSubsequence().lenLongestFibSubseq(arr);
        System.out.println(result);
    }
}
