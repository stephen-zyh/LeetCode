package Stack;

import java.util.Stack;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1021
 * @date 2022/7/26 12:15
 */
public class RemoveOutermostParentheses {
    public String removeOuterParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        int slow = -1;
        StringBuilder result = new StringBuilder();
        for (int fast = 0; fast < s.length(); fast++) {
            if (s.charAt(fast) == '('){
                stack.push(s.charAt(fast));
            }else {
                stack.pop();
                if (stack.isEmpty()){
                    //上一次添加时slow指向上一个原语的最右的')'（即前一个fast），因此每次需要加2
                    result.append(s, slow + 2, fast);
                    slow = fast;
                }
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
//        String s = "(()())(())";
        String s = "(()())(())(()(()))";
        String result = new RemoveOutermostParentheses().removeOuterParentheses(s);
        System.out.println(result);
    }
}
