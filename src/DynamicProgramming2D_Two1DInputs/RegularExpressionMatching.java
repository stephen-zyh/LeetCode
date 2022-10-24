package DynamicProgramming2D_Two1DInputs;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 10
 * @date 2022/10/24 10:15
 */
public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        //边界情况
        if (s == null || p == null || (!p.isEmpty() && p.charAt(0) == '*')){
            return false;
        }
        int len1 = s.length(), len2 = p.length();
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];   //dp[i][j]表示s的前i位与p的前j位能否匹配
        dp[0][0] = true;
        for (int i = 1; i < len2; i++) {
            //第i+1位为'*'，不管第i位是什么，只要dp[0][i - 1]为true，则dp[0][i + 1]为true（第i位匹配0次）
            if (p.charAt(i) == '*' && dp[0][i - 1]){
                dp[0][i + 1] = true;
            }
        }
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                //s的第i+1位和p的第j+1位相等，直接匹配（'.'等价于任意单个字符）
                if (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j)){
                    dp[i + 1][j + 1] = dp[i][j];
                }else if (p.charAt(j) == '*'){
                    //'*'前的字符与s的第i+1个字符不等，'*'前的字符匹配0次
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.'){
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    }else {
                        //'*'前的字符与s的第i+1个字符相等，'*'前的字符可以匹配0次、1次或多次，只要有一个匹配就算成功匹配
                        /*
                        对于dp[i][j + 1]我们可以这样想：把"c*"看做是一个整体，比如"abccc"的最后一个字符"c"和p的倒数第二个字符匹配成功，
                        因为"c*"可以匹配多个，我们就把"abccc"砍掉一个字符"c"，然后再判断"abcc"和"abc*"是否匹配。
                         */
                        dp[i + 1][j + 1] = dp[i + 1][j - 1] || dp[i + 1][j] || dp[i][j + 1];
                    }
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        String s = "ab", p = ".*";
        boolean result = new RegularExpressionMatching().isMatch(s, p);
        System.out.println(result);
    }
}
