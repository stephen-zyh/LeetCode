package DynamicProgramming2D_Two1DInputs;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 97
 * @date 2022/10/24 18:08
 */
public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        //如果len1 + len2 != len3，不管怎么拼接都得不到结果，直接返回false
        if (len1 + len2 != len3){
            return false;
        }
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];   //dp[i][j]表示s3的前i+j位能否由s1和s2的前若干位拼接而来
        dp[0][0] = true;
        //只用s1的[0,i]部分判断能否构成s3的[0,i]部分，s2同理
        for (int i = 0; i < len1 && s1.charAt(i) == s3.charAt(i); i++) {
            dp[i + 1][0] = true;
        }
        for (int j = 0; j < len2 && s2.charAt(j) == s3.charAt(j); j++) {
            dp[0][j + 1] = true;
        }
        //s3的第i+j个字符只能由其前i+j-1个字符，加上s1的第i个字符或s2的第j个字符构成，如果均不能构成，则默认为false，不做处理
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                dp[i][j] = (dp[i - 1][j] && s3.charAt(i + j - 1) == s1.charAt(i - 1))
                        || (dp[i][j - 1] && s3.charAt(i + j - 1) == s2.charAt(j - 1));
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
        boolean result = new InterleavingString().isInterleave(s1, s2, s3);
        System.out.println(result);
    }
}
