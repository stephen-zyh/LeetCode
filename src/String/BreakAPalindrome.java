package String;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1328
 * @date 2022/7/17 12:16
 */
public class BreakAPalindrome {
    public String breakPalindrome(String palindrome) {
        char[] array = palindrome.toCharArray();
        //如果该回文串长度小于等于1，无法破除
        if (palindrome.length() <= 1){
            return "";
        }
        int i;
        //顺序遍历回文串的前半截，有不是a的字母，替换为a即可返回
        for (i = 0; i < array.length / 2; i++) {
            if (array[i] != 'a'){
                array[i] = 'a';
                return String.valueOf(array);
            }
        }
        //若前半截全是a，则后半截也全是a，将最后一个a替换为b即可
        array[array.length - 1] = 'b';
        return String.valueOf(array);
    }

    public static void main(String[] args) {
        String palindrome = "aba";
//        String palindrome = "didannasayasannadid";
        System.out.println(new BreakAPalindrome().breakPalindrome(palindrome));
    }
}
