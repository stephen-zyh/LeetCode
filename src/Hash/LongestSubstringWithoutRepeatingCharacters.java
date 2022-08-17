package Hash;

import java.util.HashMap;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 3
 * @date 2022/8/7 11:40
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        int longest = 0;
        int curlen = 0;
        int start = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            //如果已经遍历的部分不包含当前字符或者与当前字符相同的字符不在子串中，当前子串长度curlen加1
            if (!map.containsKey(s.charAt(i)) || (map.containsKey(s.charAt(i)) && map.get(s.charAt(i)) < start)){
                curlen++;
            }else {
                //否则将当前找到的无重复子串长度与longest长度取最大值，将start指针移动到第一次重复的位置，更新当前长度
                longest = Math.max(longest, curlen);
                start = map.get(s.charAt(i));
                curlen = i - start;
            }
            //无论哪种情况都要将索引为i处的元素放入map中
            map.put(s.charAt(i), i);
        }
        //最后一次执行的可能是if而不是else，因此最后还需要更新一次longest
        longest = Math.max(longest, curlen);
        return longest;
    }

    public static void main(String[] args) {
        String s = "bbbbb";
        int result = new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring(s);
        System.out.println(result);
    }
}
