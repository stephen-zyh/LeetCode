package DynamicProgramming1D;

import java.util.*;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 139
 * @date 2022/10/12 17:22
 */
public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int low = 0; low <= len; low++) {
            //必须要当前位置为true才能往后面接单词
            if (!dp[low]){
                continue;
            }
            //接单词
            for (String word : wordDict){
                int high = low + word.length();
                if (high <= len && word.equals(s.substring(low, high))){
                    dp[high] = true;
                }
            }
        }
        //若单词能从头拼接到len则为true，否则为false
        return dp[len];
    }

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = new ArrayList<>(Arrays.asList("leet", "code"));
        boolean result = new WordBreak().wordBreak(s, wordDict);
        System.out.println(result);
    }
}
