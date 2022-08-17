package Queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 225
 * @date 2022/7/25 9:18
 */
public class MyStack {
    //使用双队列实现栈
    private Queue<Integer> stack1;
    private Queue<Integer> stack2;
    public MyStack() {
        stack1 = new LinkedList<>();
        stack2 = new LinkedList<>();
     }

    public void push(int x) {
        stack1.offer(x);
        while (!stack2.isEmpty()){
            stack1.offer(stack2.poll());
        }
        //stack1与stack2交换，使stack1始终为空
        Queue<Integer> temp = stack1;
        stack1 = stack2;
        stack2 = temp;
    }

    public int pop() {
        return stack2.poll();
    }

    public int top() {
        return stack2.peek();
    }

    public boolean empty() {
        return stack2.isEmpty();
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        System.out.println(myStack.top()); // 返回 2
        System.out.println(myStack.pop()); // 返回 2
        System.out.println(myStack.empty()); // 返回 False
    }
}
