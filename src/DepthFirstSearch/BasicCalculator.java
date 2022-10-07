package DepthFirstSearch;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 224，在Stack中有另外的题解
 * @date 2022/10/4 17:18
 */
public class BasicCalculator {
    public int calculate(String s) {
        Queue<Character> queue = new LinkedList<>();
        //将字符串中内容加入队列的同时去除多余的空格
        for (char c : s.toCharArray()){
            if (c != ' '){
                queue.offer(c);
            }
        }
        return evaluator(queue);
    }

    private int evaluator(Queue<Character> queue){
        Stack<Integer> stack = new Stack<>();
        int num = 0, result = 0;
        char operator = '+';
        while (!queue.isEmpty()){
            char curr = queue.poll();
            if (curr >= '0' && curr <= '9'){
                //解析数字
                num = num * 10 + (curr - '0');
            }
            //遇到'('，进行DFS，先运算'('后的表达式
            if (curr == '('){
                num = evaluator(queue);
            }
            //遇到'+'或'-'运算符，将其后的数字进行相应运算后入栈，等到')'出现，结束DFS之前对值进行累加
            if (!Character.isDigit(curr) || queue.isEmpty()){
                if (operator == '+'){
                    stack.push(num);
                }else if (operator == '-'){
                    stack.push(-num);
                }
                //修改当前运算符，并将num置0，以便读取下一个数字
                operator = curr;
                num = 0;
            }
            if (curr == ')'){
                break;
            }
        }
        //计算括号内运算的结果
        while (!stack.isEmpty()){
            result += stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "(3)+1 ";
        int result = new BasicCalculator().calculate(s);
        System.out.println(result);
    }
}
