package String;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 14
 * @date 2022/7/10 9:03
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0){
            return "";
        }
        String longeststr = strs[0];
        int count = 0;
        for (int i = strs[0].length(); i >= 0; i--) {
            longeststr = strs[0].substring(0, i);
            for (String str : strs) {
                if (str.startsWith(longeststr)) {
                    count++;
                } else {
                    break;
                }
            }
            if (count == strs.length){
                break;
            }else {
                count = 0;
            }
        }
        return longeststr;
    }

    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
//        String[] strs = {"dog","racecar","car"};
//        String[] strs = {"a"};
        String longest = new LongestCommonPrefix().longestCommonPrefix(strs);
        System.out.println(longest);
    }
}
