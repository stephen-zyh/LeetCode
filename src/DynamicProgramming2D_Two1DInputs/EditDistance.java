package DynamicProgramming2D_Two1DInputs;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 72
 * @date 2022/10/24 9:58
 */
public class EditDistance {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];   //dp[i][j]表示s1的前i位增加、删除、修改某些字符得到s2的前j位所需要的步骤
        //让长度为i的字符串变为空串要经过i次操作
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                //增加或删除
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + 1;
                if (word1.charAt(i - 1) == word2.charAt(j - 1)){
                    //不对word1.charAt(i - 1)和word2.charAt(j - 1)进行操作，不增加代价
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }else {
                    //替换word1.charAt(i - 1)或word2.charAt(j - 1)使得两串相等
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        String word1 = "intention", word2 = "execution";
        int result = new EditDistance().minDistance(word1, word2);
        System.out.println(result);
    }
}
