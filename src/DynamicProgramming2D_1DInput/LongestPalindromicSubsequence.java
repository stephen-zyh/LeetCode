package DynamicProgramming2D_1DInput;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 516
 * @date 2022/10/25 11:16
 */
public class LongestPalindromicSubsequence {
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len]; //dp[i][j]：i到j的最长回文子序列长度
        for (int i = len - 1; i >= 0 ; i--) {
            dp[i][i] = 1;   //长度为1的回文子序列
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)){
                    //i和j处的字符相等时两个都进行添加，序列长度加2
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }else {
                    //i和j不相等时，添加其中一个（不能不添加），取其中的大值
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][len - 1];
    }

    public static void main(String[] args) {
        String s = "ababa";
        int result = new LongestPalindromicSubsequence().longestPalindromeSubseq(s);
        System.out.println(result);
    }
}
