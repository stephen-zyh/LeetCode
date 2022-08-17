package String;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 28
 * @date 2022/7/10 9:37
 */
public class strStrImplement {
    //如果想偷懒，可以直接 return haystack.indexOf(needle);
    public int strStr(String haystack, String needle) {
        int i = 0, j = 0;
        //要找的子串比原串还长，直接结束
        if (haystack.length() < needle.length()){
            return -1;
        }
        while (i < haystack.length() && j < needle.length()){
            char ch_h = haystack.charAt(i);
            char ch_n = needle.charAt(j);
            if (ch_h == ch_n){
                i++;
                j++;
            }else{
                i = i - j + 1;
                j = 0;
            }
        }
        //如果找到匹配，子串被遍历完，指针值应当为其长度
        if (j != needle.length()){
            return -1;
        }
        return i - j;
    }

    public static void main(String[] args) {
        String haystack = "hello", needle = "ll";
//        String haystack = "aaaaa", needle = "bba";
        System.out.println(new strStrImplement().strStr(haystack, needle));
    }
}
