package String;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 557
 * @date 2022/7/12 9:23
 */
public class ReverseWordsInStringIII {
    public String reverseWords(String s) {
        char[] str = s.toCharArray();
//        reverse(str, 0, str.length - 1);
        int left = 0, right = 0;
        while (right < str.length){
            if (str[right] == ' '){
                reverse(str, left, right - 1);
                left = right + 1;
            }
            right++;
        }
        reverse(str, left, right - 1);
        return String.valueOf(str);
    }

    public void reverse(char[] c, int start, int end){
        char temp;
        for(int i = start, j = end; i < j; i++, j--){
            temp = c[i];
            c[i] = c[j];
            c[j] = temp;
        }
    }

    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
        System.out.println(new ReverseWordsInStringIII().reverseWords(s));
    }
}
