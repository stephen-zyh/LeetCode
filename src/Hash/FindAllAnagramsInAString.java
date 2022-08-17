package Hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 438，滑动窗口
 * @date 2022/8/8 16:31
 */
public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        HashMap<Character, Integer> need = new HashMap<>(); //p中字母和其出现次数的映射
        HashMap<Character, Integer> window = new HashMap<>();   //need中包含的字母在窗口中的出现次数
        for (int i = 0; i < p.length(); i++) {
            need.put(p.charAt(i), need.getOrDefault(p.charAt(i), 0) + 1);
        }
        ArrayList<Integer> result = new ArrayList<>();
        int left = 0, right = 0;
        int valid = 0;  //window中包含的有效字符的数目
        while (right < s.length()){
            char ch1 = s.charAt(right);
            right++;
            //need中有的字符才纳入计算
            if (need.containsKey(ch1)){
                window.put(ch1, window.getOrDefault(ch1, 0) + 1);   //字符出现的真实数目
                if (need.get(ch1).intValue() == window.get(ch1).intValue()){
                    valid++;
                }
            }
            //窗口长度大于字符串长度，左指针移动
            while (right - left >= p.length()){
                if (valid == need.size()){
                    result.add(left);
                }
                char ch2 = s.charAt(left);
                left++;
                if (need.containsKey(ch2)){
                    if (need.get(ch2).intValue() == window.get(ch2).intValue()){
                        valid--;
                    }
                    window.put(ch2, window.get(ch2) - 1);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd", p = "abc";
        List<Integer> list = new FindAllAnagramsInAString().findAnagrams(s, p);
        System.out.println(list);
    }
}
