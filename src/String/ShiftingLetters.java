package String;

import java.util.Arrays;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 848, 为避免超出时间限制，本题的时间复杂度应控制在O(n)
 * @date 2022/7/15 9:34
 */
public class ShiftingLetters {
    public String shiftingLetters(String s, int[] shifts) {
        char[] c = s.toCharArray();
        //shifts数组各位的值修改为s中对应位应当移动的位数，运算应当是模26的
        for (int i = shifts.length - 2; i >= 0; i--) {
            shifts[i] = (shifts[i] + shifts[i + 1]) % 26;
        }
        //对字母进行移位
        for (int i = 0; i < c.length; i++) {
                c[i] = shift(c[i], shifts[i]);
        }
        return String.valueOf(c);
    }

    public char shift(char letter, int key){
        return (char)((letter - 'a' + key) % 26 + 'a');
    }

    public static void main(String[] args) {
//        String s = "abc";
//        int[] shifts = {3,5,9};
        String s = "aaa";
        int[] shifts = {1,2,3};
        System.out.println(new ShiftingLetters().shiftingLetters(s, shifts));
    }
}
