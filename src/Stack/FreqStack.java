package Stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 895
 * @date 2022/8/5 20:38
 */
public class FreqStack {
    Map<Integer, Integer> freq; //记录一个元素出现的频率
    Map<Integer, Stack<Integer>> stackMap;  //出现频次和储存对应频率的元素的栈的映射
    int mostFreqent = 0;
    public FreqStack() {
        freq = new HashMap<>();
        stackMap = new HashMap<>();
    }

    public void push(int val) {
        //该元素出现次数加1，更新出现频率和最大的频次
        int freqency = freq.getOrDefault(val, 0) + 1;
        freq.put(val, freqency);
        if (freqency > mostFreqent){
            mostFreqent = freqency;
        }
        //如果对应频次的频次和栈的映射未创建则创建，并压入对应的栈
        stackMap.computeIfAbsent(freqency, z -> new Stack<>()).push(val);
    }

    public int pop() {
        //从记录出现频次最高的栈中弹出栈顶元素，并将对应元素出现频次减1
        Stack<Integer> stack = stackMap.get(mostFreqent);
        int result = stack.pop();
        freq.put(result, mostFreqent - 1);
        //如果最高频次的栈在出栈后为空，则最高频次减1
        if (stack.size() == 0){
            mostFreqent--;
        }
        return result;
    }

    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
        freqStack.push (5);//堆栈为 [5]
        freqStack.push (7);//堆栈是 [5,7]
        freqStack.push (5);//堆栈是 [5,7,5]
        freqStack.push (7);//堆栈是 [5,7,5,7]
        freqStack.push (4);//堆栈是 [5,7,5,7,4]
        freqStack.push (5);//堆栈是 [5,7,5,7,4,5]
        System.out.println(freqStack.pop ());//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,5,7,4]。
        System.out.println(freqStack.pop ());//返回 7 ，因为 5 和 7 出现频率最高，但7最接近顶部。堆栈变成 [5,7,5,4]。
        System.out.println(freqStack.pop ());//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,4]。
        System.out.println(freqStack.pop ());//返回 4 ，因为 4, 5 和 7 出现频率最高，但 4 是最接近顶部的。堆栈变成 [5,7]。
    }
}
