package String;

/**
 * @author zhaoyh
 * @version 1.0
 *  @description: leetcode 334
 * @date 2022/7/7 16:07
 */
public class ReverseString {
    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        char temp;
        while (left <= right){
            temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        char[] s = {'h','e','l','l','o'};
        new ReverseString().reverseString(s);
        System.out.println(s);
    }
}
