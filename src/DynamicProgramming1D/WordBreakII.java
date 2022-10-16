package DynamicProgramming1D;

import java.util.*;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 140，参考leetcode 139
 * @date 2022/10/13 11:11
 */
public class WordBreakII {
    List<String> result = new ArrayList<>();
    Set<String> wordSet;
    public List<String> wordBreak(String s, List<String> wordDict) {
        //先用DP判断是否能拼接，能则进行拼接
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int low = 0; low <= len; low++) {
            if (!dp[low]){
                continue;
            }
            for (String word : wordDict){
                int high = low + word.length();
                if (high <= len && word.equals(s.substring(low, high))){
                    dp[high] = true;
                }
            }
        }
        //不能拼出整个字符串，返回空列表
        if (!dp[len]){
            return result;
        }
        wordSet = new HashSet<>(wordDict);
        //回溯法添加结果
        addResult(s, 0, new StringBuilder());
        return result;
    }

    private void addResult(String s, int startIndex, StringBuilder builder){
        int len = s.length();
        //走到字符串的结尾，说明找到一个结果，添加到结果中并返回
        if (len == startIndex){
            result.add(builder.toString());
            return;
        }
        for (int i = startIndex; i < len; i++) {
            if (wordSet.contains(s.substring(startIndex, i + 1))){
                int original = builder.length();    //记录builder原长，以便回溯
                //分builder是否为空进行处理，第一次添加单词时不用添加空格，余下的每次添加时先添加空格
                if (original == 0){
                    builder.append(s, startIndex, i + 1);
                }else {
                    builder.append(' ').append(s, startIndex, i + 1);
                }
                addResult(s, i + 1, builder);
                builder.delete(original, builder.length());
            }
        }
    }

    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<>(Arrays.asList("cat","cats","and","sand","dog"));
        List<String> result = new WordBreakII().wordBreak(s, wordDict);
        System.out.println(result);
    }
}
