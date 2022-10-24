package DynamicProgramming2D_Two1DInputs;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1143
 * @date 2022/10/21 10:03
 */
public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        //dp[i][j] represents the longest common subsequence of text1[0 ... i] & text2[0 ... j].
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                //该字符可以加入LCS
                if (text1.charAt(i - 1) == text2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else {
                    //该位置的字符不相等，至少有一个不能加入LCS，先选择当前局部最优解，即选择前面的较大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        String text1 = "abcde", text2 = "ace";
        int result = new LongestCommonSubsequence().longestCommonSubsequence(text1, text2);
        System.out.println(result);
    }
}
