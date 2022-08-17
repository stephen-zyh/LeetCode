package Hash;

import java.util.HashMap;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 340，滑动窗口
 * @date 2022/8/11 9:21
 */
public class LongestSubstringWithAtMostKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        //题目没给k的范围，但确实有k = 0的用例
        if (k ==0){
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();  //字母和其出现次数的映射
        int left = 0, right = 0;
        int longest = 0;
        while (right < s.length()){
            char curr = s.charAt(right);
            //map中已经包含该字母，则出现次数加1，否则如果出现的字母种类不足k，则将该字母放入map，出现次数为1
            //否则记录right-left并与原来的longest取大者
            if (map.containsKey(curr)){
                map.put(curr, map.get(curr) + 1);
            }else if (map.size() < k){
                map.put(curr, 1);
            }else {
                longest = Math.max(longest, right - left);
                while (true){
                    //随着left指针的移动减小对应字母出现的次数，当出现第一个出现次数减为0的字母时移除该字母并跳出循环
                    char rmv = s.charAt(left);
                    left++;
                    map.put(rmv, map.get(rmv) - 1);
                    if (map.get(rmv) == 0){
                        map.remove(rmv);
                        break;
                    }
                }
                //移除rmv后map.size为k-1，再将curr添加到map中
                map.put(curr, 1);
            }
            right++;
        }
        //考虑到上面的else可能一次都没有执行过，还需要最后更新一次longest
        longest = Math.max(longest, right - left);
        return longest;
    }

    public static void main(String[] args) {
        String s = "eceba";
        int k = 2;
        int result = new LongestSubstringWithAtMostKDistinctCharacters().lengthOfLongestSubstringKDistinct(s, k);
        System.out.println(result);
    }
}
