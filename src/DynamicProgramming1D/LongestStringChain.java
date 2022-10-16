package DynamicProgramming1D;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1048
 * @date 2022/10/15 16:14
 */
public class LongestStringChain {
    public int longestStrChain(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        int result = 0;
        Arrays.sort(words, Comparator.comparingInt(String::length));    //按单词长度排序
        for (String word : words){
            int len = word.length();
            int temp = 1;
            //检查删除原有字符串中的一位之后的子串是否在map中如果有，则单词链的长度应该为子串中最长的单词链长度加1；否则单词链长度改为1
            for (int i = 0; i < len; i++) {
                String curr = word.substring(0, i) + word.substring(i + 1);
                if (map.containsKey(curr)){
                    temp = Math.max(temp, map.get(curr) + 1);
                }
            }
            map.put(word, temp);
            //更新result，其值应该为所有单词链长度的最大者
            result = Math.max(temp, result);
        }
        return result;
    }

    public static void main(String[] args) {
        String[] words = {"a","b","ba","bca","bda","bdca"};
        int result = new LongestStringChain().longestStrChain(words);
        System.out.println(result);
    }
}
