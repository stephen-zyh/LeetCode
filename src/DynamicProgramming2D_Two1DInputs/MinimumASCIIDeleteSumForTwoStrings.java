package DynamicProgramming2D_Two1DInputs;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 712
 * @date 2022/10/24 8:56
 */
public class MinimumASCIIDeleteSumForTwoStrings {
    public int minimumDeleteSum(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];   //  dp[i][j]表示使s1前i位和s2前j位删除到相等的最小代价
        //让长为i的字符串变为空串的代价是字符串各位上的字符ascii值相加
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                //如果s1.charAt(i - 1) == s2.charAt(j - 1)，不删除任意一边的字符是最佳选择
                if (s1.charAt(i - 1) == s2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                }else {
                    //否则在删除s1的第i位和删除s2的第j位得到的代价取最小值
                    dp[i][j] = Math.min(dp[i - 1][j] + s1.charAt(i - 1), dp[i][j - 1] + s2.charAt(j - 1));
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        String s1 = "sea", s2 = "eat";
        int result = new MinimumASCIIDeleteSumForTwoStrings().minimumDeleteSum(s1, s2);
        System.out.println(result);
    }
}
