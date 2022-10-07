package DepthFirstSearch;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 439
 * @date 2022/10/4 15:10
 */
public class TernaryExpressionParser {
    public String parseTernary(String expression) {
        int c1 = 0, c2 = 0;
        for (int i = 1; i < expression.length(); i++) {
            if (expression.charAt(i) == '?'){
                c1++;
            }else if (expression.charAt(i) == ':'){
                c2++;
            }
            //将表达式分为两截，分别递归处理
            if (c1 == c2){
                return expression.charAt(0) == 'T' ?
                        parseTernary(expression.substring(2, i)) : parseTernary(expression.substring(i + 1));
            }
        }
        //若表达式长度为1，直接返回即可
        return expression;
    }

    public static void main(String[] args) {
        String expression = "T?T?F:5:3";
        String result = new TernaryExpressionParser().parseTernary(expression);
        System.out.println(result);
    }
}
