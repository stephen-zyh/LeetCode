package BoyerMooreVotingAlgorithm;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 159，实际上是滑动窗口
 * @date 2022/11/3 20:41
 */
public class LongestSubstringWithAtMostTwoDistinctCharacters {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int[] times = new int[128];
        int distinct = 0;   //统计出现字符的种类数
        int left = 0, right = 0;
        int length = s.length();
        int result = 0;
        while (right < length) {
            char curr = s.charAt(right);
            //left到right出现新的字母，distinct加1
            if (++times[curr] == 1){
                distinct++;
            }
            //出现三个不同的字符时改变左边界，不断删除左侧字符，当只有两种字符时停止操作并记录长度
            while (distinct == 3){
                if (--times[s.charAt(left++)] == 0){
                    distinct--;
                }
            }
            result = Math.max(result, right - left + 1);
            right++;
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "ccaabbb";
        int result = new LongestSubstringWithAtMostTwoDistinctCharacters().lengthOfLongestSubstringTwoDistinct(s);
        System.out.println(result);
    }
}
