package DynamicProgramming2D_Two1DInputs;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 115
 * @date 2022/10/21 11:17
 */
public class DistinctSubsequences {
    public int numDistinct(String s, String t) {
        int len1 = s.length(), len2 = t.length();
        //如果两个字符串长度相等，不用考虑删除字符，直接比较字符串是否相等即可
        if (len1 == len2){
            return s.equals(t) ? 1: 0;
        }
        //如果len1 < len2，不可能通过删除s1中字符来得到s2
        if (len1 < len2)    return 0;
        int[][] dp = new int[len1 + 1][len2 + 1];   //dp[i][j]表示s1的前i位删除某些字符得到s2的前j位所需要的步骤
        //边界情况：将s1的前i位变为空串只有一种办法
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = 1;
        }
        //对于s1中的每个字母，都有删与不删两种情况
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                //s.charAt(i - 1)与t.charAt(j - 1)不等时，s.charAt(i - 1)必定要删除，相等时分删除和不删除两种情况，方法数是二者之和
                dp[i][j] = dp[i - 1][j];
                if (s.charAt(i - 1) == t.charAt(j - 1)){
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        String s = "rabbbit", t = "rabbit";
        int result = new DistinctSubsequences().numDistinct(s, t);
        System.out.println(result);
    }
}
