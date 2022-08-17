package Stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 739
 * @date 2022/7/28 11:13
 */
public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Arrays.fill(result, 0);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            //栈不空且下一个元素更大时可出栈（栈中存放的是索引值），出栈完后当前元素入栈
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]){
                int top = stack.pop();
                result[top] = i - top;  //下一个更高气温与当前的天数差
            }
            stack.push(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] temperatures = {73,74,75,71,69,72,76,73};
        int[] result = new DailyTemperatures().dailyTemperatures(temperatures);
        System.out.println(Arrays.toString(result));
    }
}
