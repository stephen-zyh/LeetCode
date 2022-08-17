package Stack;

import java.util.Stack;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 394
 * @date 2022/7/30 18:27
 */
public class DecodeString {
    public String decodeString(String s) {
        StringBuilder builder = new StringBuilder();
        Stack<Integer> timesStack = new Stack<>();  //记录重复次数
        Stack<String> tempString = new Stack<>();   //记录'['前的子串
        for (int i = 0; i < s.length(); i++) {
            //由于重复次数可能大于10，需要计算重复的次数
            if (Character.isDigit(s.charAt(i))){
                int times = s.charAt(i) - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))){
                    times = times * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                timesStack.push(times);
            }else if (s.charAt(i) == '['){
                //遇到'['，需要将前面的串压入栈内保存
                tempString.push(builder.toString());
                builder = new StringBuilder();
            }else if (s.charAt(i) == ']'){
                //遇到']'，将栈顶元素与解码后的当前串拼接
                StringBuilder temp = new StringBuilder(tempString.pop());
                temp.append(builder.toString().repeat(timesStack.pop()));
                builder = temp;
            }else {
                builder.append(s.charAt(i));
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
//        String s = "3[a]2[bc]";
//        String s = "3[a2[c]]";
        String s = "2[abc]3[cd]ef";
        String decodeString = new DecodeString().decodeString(s);
        System.out.println(decodeString);
    }
}
