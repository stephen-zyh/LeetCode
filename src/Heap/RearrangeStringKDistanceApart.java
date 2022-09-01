package Heap;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 358
 * @date 2022/8/29 11:52
 */
public class RearrangeStringKDistanceApart {
    public String rearrangeString(String s, int k) {
        StringBuilder builder = new StringBuilder();
        int[] times = new int[26];  //记录字符串中各个字符出现的次数
        int mostFrequent = 0;   //记录字符串中出现次数最多的字符的次数
        for (int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);
            times[currChar - 'a']++;
            mostFrequent = Math.max(mostFrequent, times[currChar - 'a']);
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> times[o2] - times[o1]);   //大根堆
        for (int i = 0; i < times.length; i++) {
            //只将有出现的字母加入优先级队列，避免空间浪费
            if (times[i] > 0){
                heap.offer(i);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        while (!heap.isEmpty()){
            int curr = heap.poll(); //优先安排出现次数最多的字符
            builder.append((char) (curr + 'a'));
            times[curr]--;  //更新curr出现的次数
            queue.offer(curr);
            //queue用于保存k个不同元素，由于queue先进先出的性质，先安排过的字符在间距达到k后又可以加入到堆中
            if (queue.size() < k){
                continue;
            }

            int head = queue.poll();
            if (times[head] > 0){
                heap.offer(head);
            }
        }
        return builder.length() == s.length() ? builder.toString() : "";
    }

    public static void main(String[] args) {
        String s = "aaadbbcc";
        int k = 2;
        String result = new RearrangeStringKDistanceApart().rearrangeString(s, k);
        System.out.println(result);
    }
}
