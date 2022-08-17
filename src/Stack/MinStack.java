package Stack;

import java.util.Stack;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 155
 * @date 2022/7/27 7:30
 */
public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minValue;
    public MinStack() {
        stack = new Stack<>();
        minValue = new Stack<>();   //因为随着出栈和入栈操作的进行，栈中最小值也会发生改变，栈顶为当前stack中最小值
    }

    public void push(int val) {
        stack.push(val);
        if (minValue.isEmpty()){
            minValue.push(val); //栈中没有元素，val就是最小的元素
        }else if (val <= minValue.peek()){
            //如果val比minValue的栈顶更大，stack中最小值不会变；反之，将val添加到minValue中
            //因为可能连续push多个相同的值，要保证不是所有该相同元素都出栈时栈中最小值不变，
            //因此在val == minValue.peek()时，也应当minValue.push(val)
            minValue.push(val);
        }
    }

    public void pop() {
        Integer popValue = stack.pop();
        //出栈的值是栈中最小值，minValue也应当有出栈操作
        if (popValue.equals(minValue.peek())){
            minValue.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minValue.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());   // 返回 -3.
        minStack.pop();
        System.out.println(minStack.top());      // 返回 0.
        System.out.println(minStack.getMin());   // 返回 -2.
    }
}
