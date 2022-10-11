package Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 22，在Stack包中的ValidParentheses类有对该题的解
 * @date 2022/10/7 10:30
 */
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(0, 0, n, result, new StringBuilder());
        return result;
    }

    private void generate(int left, int right, int n, List<String> result, StringBuilder builder){
        if (builder.length() == 2 * n){
            //s的长度已经是2n说明已经得到一个结果，可以返回
            result.add(builder.toString());
        }
        //先添加左括号
        if (left < n){
            builder.append('(');
            generate(left + 1, right, n, result, builder);
            builder.deleteCharAt(builder.length() - 1);
        }
        //每一次右括号数目不能比左括号多，否则括号不能配对
        if (right < left){
            builder.append(')');
            generate(left, right + 1, n, result, builder);
            builder.deleteCharAt(builder.length() - 1);
        }
    }

    public static void main(String[] args) {
        List<String> list = new GenerateParentheses().generateParenthesis(3);
        System.out.println(list);
    }
}
