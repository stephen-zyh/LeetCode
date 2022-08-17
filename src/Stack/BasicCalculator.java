package Stack;

import java.util.Stack;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 224
 * @date 2022/8/1 11:31
 */
public class BasicCalculator {
    public int calculate(String s) {
        int result = 0;
        Stack<Integer> numStack = new Stack<>();    //记录参与运算的数字
        Stack<Character> sign = new Stack<>();  //记录运算符
        for (int i = 0; i < s.length(); i++) {
            //忽略串中的空格
            if (s.charAt(i) == ' '){
                continue;
            }
            //记录运算符
            if (s.charAt(i) == '+' || s.charAt(i) == '-'){
                sign.push(s.charAt(i));
            }
            else if (Character.isDigit(s.charAt(i))){
                //参与运算的数字范围与int相同，可能不止一位，需要从字符串中提取出来
                int number = s.charAt(i) - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))){
                    number = number * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                if (sign.isEmpty() || sign.peek() == '('){
                    result = number;    //数字前面没有运算符，或者数字前面有运算符，但数字在'('后面，不能使用'('前的运算符
                }else if (sign.pop() == '+'){
                    result += number;
                }else {
                    result -= number;
                }
            }else if (s.charAt(i) == '('){
                numStack.push(result);  //'('左侧的运算结果暂存
                sign.push(s.charAt(i)); //'('需要入栈，作为上述分支判断条件
                result = 0;
            }else {
                sign.pop(); //当前字符是')'，应当将对应的'('从栈中弹出
                if (!numStack.isEmpty() && !sign.isEmpty()){
                    int temp = numStack.pop();
                    //'('前的内容与"()"中的内容做运算
                    if (sign.pop() == '+'){
                        result = temp + result;
                    }else {
                        result = temp - result;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        String s = "1 + 1";
//        String s = " 2-1 + 2 ";
        String s = "(1+(4+5+2)-3)+(6+8)";
        int result = new BasicCalculator().calculate(s);
        System.out.println(result);
    }
}
