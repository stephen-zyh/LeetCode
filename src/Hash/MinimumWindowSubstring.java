package Hash;

import java.util.HashMap;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 76，滑动窗口
 * @date 2022/8/11 11:48
 */
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> need = new HashMap<>(); //字符串t中字符与其出现次数的映射
        HashMap<Character, Integer> window = new HashMap<>();   //滑动窗口window中字符与其出现次数的映射
        int left = 0, right = 0;
        int start = 0, end = s.length();    //最短匹配在s中的开始和结束索引
        int valid = 0, minLen = s.length(); //valid为window中有效字符的个数，minLen是最短匹配的长度

        for (int i = 0; i < t.length(); i++) {
            need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
        }

        while (right < s.length()){
            char curr = s.charAt(right);
            right++;
            if (need.containsKey(curr)){
                window.put(curr, window.getOrDefault(curr, 0) + 1);
                //window中curr出现的次数等于need中的出现次数时，有效字符个数valid即可加1
                if (need.get(curr).intValue() == window.get(curr).intValue()){
                    valid++;
                }
            }else {
                //不是need中的字符遍历但不作统计，结束本轮循环
                continue;
            }
            //所有字符都出现了相应次数后移动左边界
            while (valid == need.size()){
                char rmv = s.charAt(left);
                //rmv不在need中时，直接移动left即可
                //rmv在need中时，如果rmv的出现次数大于所需则将其出现次数减1，等于所需时更新start、end和minLen并跳出循环
                if (need.containsKey(rmv)){
                    if (window.get(rmv) > need.get(rmv)){
                        window.put(rmv, window.get(rmv) - 1);
                    }else {
                        if (right - left < minLen){
                            minLen = right - left;
                            start = left;
                            end = right;
                        }
                        break;
                    }
                }
                left++;
            }
        }
        //没有找到包含所有字符的窗口则返回空，否则返回s从索引start到end的子串
        if (valid < need.size()){
            return "";
        }
        return s.substring(start, end);
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";
        String result = new MinimumWindowSubstring().minWindow(s, t);
        System.out.println(result);
    }
}
