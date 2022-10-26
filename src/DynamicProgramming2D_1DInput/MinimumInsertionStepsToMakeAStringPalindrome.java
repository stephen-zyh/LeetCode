package DynamicProgramming2D_1DInput;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1312，参考leetcode 516
 * @date 2022/10/25 11:32
 */
public class MinimumInsertionStepsToMakeAStringPalindrome {
    //总思路：找出其中最长的回文子序列长度，剩下的落单的元素的个数即是需要执行插入操作的次数
    public int minInsertions(String s) {
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
        //落单的元素的个数即是需要执行插入操作的次数
        return len - dp[0][len - 1];
    }

    public static void main(String[] args) {
        String s = "leetcode";
        int result = new MinimumInsertionStepsToMakeAStringPalindrome().minInsertions(s);
        System.out.println(result);
    }
}
