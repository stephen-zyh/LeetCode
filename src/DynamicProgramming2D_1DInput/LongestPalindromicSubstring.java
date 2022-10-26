package DynamicProgramming2D_1DInput;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 5
 * @date 2022/10/25 10:25
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        int len = s.length();
        int max = 1, left = 0, right = 1;
        int[][] dp = new int[len][len];
        for(int i = len - 1; i >= 0; i--){
            for(int j = i; j < len; j++){
                if(i == j){
                    dp[i][j] = 1;
                    continue;
                }
                if(s.charAt(i) == s.charAt(j)){
                    //i + 1 == j，回文串长度为2
                    if(i + 1 == j){
                        dp[i][j] = 2;
                    }else if(dp[i + 1][j - 1] != 0){
                        //两侧字符相等，在原有回文串长度基础上加2
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    }
                    //更新最长回文串的起始和终止下标
                    if(dp[i][j] >= max){
                        max = dp[i][j];
                        left = i;
                        right = j + 1;
                    }
                }
            }
        }
        return s.substring(left, right);
    }

    public static void main(String[] args) {
        String s = "babad";
        String result = new LongestPalindromicSubstring().longestPalindrome(s);
        System.out.println(result);
    }
}
