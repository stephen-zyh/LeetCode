package Stack;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1249
 * @date 2022/7/26 10:47
 */
public class MinimumRemove2MakeValidParentheses {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> char2Remove = new HashSet<>(); //char2remove用于存放应当去除的括号的索引
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '('){
                //将所有出现的'('压栈，到出现')'时出栈
                stack.push(i);
            }else if (s.charAt(i) == ')' && stack.isEmpty()){
                //栈里没有'('可以与')'配对，对应索引处的')'将被移除
                char2Remove.add(i);
            }else if (s.charAt(i) == ')'){
                stack.pop();
            }
        }
        while (!stack.isEmpty()){
            //此时栈里余下的是没有配对的'('的索引
            char2Remove.add(stack.pop());
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length() ; i++) {
            if (char2Remove.contains(i)){
                continue;
            }
            builder.append(s.charAt(i));
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String s = "lee(t(c)o)de)";
//        String s = "a)b(c)d";
//        String s = "))((";
        String result = new MinimumRemove2MakeValidParentheses().minRemoveToMakeValid(s);
        System.out.println(result);
    }
}
