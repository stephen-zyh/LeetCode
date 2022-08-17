package Stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 22
 * @date 2022/7/25 12:27
 */
public class ValidParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(0, 0, n, result, "");
        return result;
    }

    public void generate(int left, int right, int n, List<String> result, String s){
        if (s.length() == 2 * n){
            //s的长度已经是2n说明已经得到一个结果，可以返回
            result.add(s);
        }
        //先添加左括号
        if (left < n){
            generate(left + 1, right, n, result, s + '(');
        }
        //每一次右括号数目不能比左括号多，否则括号不能配对
        if (right < left){
            generate(left, right + 1, n, result, s + ')');
        }
    }

    public static void main(String[] args) {
        List<String> result = new ValidParentheses().generateParenthesis(3);
        System.out.println(Arrays.toString(result.toArray()));
    }
}
