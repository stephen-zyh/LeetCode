package String;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 151
 * @date 2022/7/11 11:19
 */
public class ReverseWordsInString {
    public String reverseWords(String s) {
        String[] strings = s.split(" +");
        StringBuilder builder = new StringBuilder();
        for (int i = strings.length - 1; i >= 0; i--){
            builder.append(strings[i]).append(" ");
        }
        return builder.toString().trim();
    }

    public static void main(String[] args) {
//        String s = "the sky is blue";
        String s = "  hello world  ";
//        String s = "a good   example";
        System.out.println(new ReverseWordsInString().reverseWords(s));
    }
}
