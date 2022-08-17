package Stack;

import java.util.Stack;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 32
 * @date 2022/8/2 10:04
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        char[] chars = s.toCharArray();
        int maxResult = 0;
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            //'('都入栈，当遇到能配对的')'时将两处的值置为0，最终连续的0代表连续的括号区域
            if (s.charAt(i) == '('){
                stack.push(i);
            }else if (!stack.isEmpty()){
                chars[i] = 0;
                chars[stack.pop()] = 0;
            }
        }
        for (char aChar : chars) {
            if (aChar == 0) {
                result++;
            } else {
                maxResult = Math.max(maxResult, result);
                result = 0;
            }
        }
        //chars最后几个元素可能是连续的0，这种情况未在if-else中得到比较，需最后单独比较一次
        maxResult = Math.max(maxResult, result);
        return maxResult;
    }

    public static void main(String[] args) {
//        String s = "(()";
//        String s = ")()())";
        String s = "()(()";
        int result = new LongestValidParentheses().longestValidParentheses(s);
        System.out.println(result);
    }
}
