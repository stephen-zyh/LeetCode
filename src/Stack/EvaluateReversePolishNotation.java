package Stack;

import java.util.Stack;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 150
 * @date 2022/8/1 18:07
 */
public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        int result;
        int x, y;
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            switch (token) {
                case "+" -> {
                    x = stack.pop();
                    y = stack.pop();
                    result = x + y;
                    stack.push(result);
                }
                case "-" -> {
                    x = stack.pop();
                    y = stack.pop();
                    result = y - x;
                    stack.push(result);
                }
                case "*" -> {
                    x = stack.pop();
                    y = stack.pop();
                    result = y * x;
                    stack.push(result);
                }
                case "/" -> {
                    x = stack.pop();
                    y = stack.pop();
                    result = y / x;
                    stack.push(result);
                }
                default -> stack.push(Integer.parseInt(token));
            }
        }
        //由于输入的字符串数组可能只有一个数字而没有运算符，此时栈顶的元素即是最终答案
        //除此之外的情况也满足该表达式，因为每次运算后结果会存到栈中
        return stack.peek();
    }

    public static void main(String[] args) {
        String[] tokens = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        int result = new EvaluateReversePolishNotation().evalRPN(tokens);
        System.out.println(result);
    }
}
