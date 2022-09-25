package BinarySearch;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1062
 * @date 2022/9/19 8:16
 */
public class LongestRepeatingSubstring {
    public int longestRepeatingSubstring(String s) {
        int left = 0, right = s.length() - 1, middle;
        while (left < right){
            middle = left + (right - left + 1) / 2; //对middle上取整，避免陷入死循环
            //检查middle长度的字符串是否在s中重复
            if (!isRepeated(middle, s)){
                right = middle - 1;
            }else {
                left = middle;
            }
        }
        return left;
    }

    //检查特定长度字符串在原字符串中是否重复出现
    private boolean isRepeated(int length, String s){
        Set<String> set = new HashSet<>();
        for (int i = 0; i <= s.length() - length; i++) {
            String temp = s.substring(i, i + length);
            //如果set中包含该字符串，说明已经重复，返回true
            if (set.contains(temp)) return true;
            set.add(temp);
        }
        //找遍整个字符串没有出现重复，返回false
        return false;
    }

    public static void main(String[] args) {
        String s = "aabcaabdaab";
        int result = new LongestRepeatingSubstring().longestRepeatingSubstring(s);
        System.out.println(result);
    }
}
