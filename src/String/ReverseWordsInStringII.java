package String;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 186
 * @date 2022/7/12 8:58
 */
public class ReverseWordsInStringII {
    public void reverseWords(char[] str) {
        reverse(str, 0, str.length - 1);
        int left = 0, right = 0;
        while (right < str.length){
            if (str[right] == ' '){
                reverse(str, left, right - 1);
                left = right + 1;
            }
            right++;
        }
        reverse(str, left, right - 1);
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
        char[] str = {'t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e'};
        new ReverseWordsInStringII().reverseWords(str);
        System.out.println(str);
    }
}
