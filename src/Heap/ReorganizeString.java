package Heap;

import java.util.PriorityQueue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 767
 * @date 2022/8/27 9:47
 */
public class ReorganizeString {
    public String reorganizeString(String s) {
        int[] times = new int[26];  //记录字符串中各个字符出现的次数
        int mostFrequent = 0;   //记录字符串中出现次数最多的字符的次数
        for (int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);
            times[currChar - 'a']++;
            mostFrequent = Math.max(mostFrequent, times[currChar - 'a']);
        }
        //如果某个字母出现的次数超过了(s.length() + 1) / 2，则无法重构字符串
        if (mostFrequent > (s.length() + 1) / 2){
            return "";
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> times[o2] - times[o1]);  //大根堆
        for (int i = 0; i < times.length; i++) {
            //只将有出现的字母加入优先级队列，避免空间浪费
            if (times[i] > 0){
                queue.offer(i);
            }
        }
        StringBuilder builder = new StringBuilder();
        while (queue.size() > 1){
            //每次将出现次数最多的两个字母添加到结果中
            int index1 = queue.poll();
            int index2 = queue.poll();

            builder.append((char) ('a' + index1));
            builder.append((char) ('a' + index2));

            //更新字母出现次数，若出现次数仍大于0则重新放入堆中
            times[index1]--;
            times[index2]--;

            if (times[index1] > 0){
                queue.offer(index1);
            }
            if (times[index2] > 0){
                queue.offer(index2);
            }
        }
        //堆中元素可能是奇数个
        if (!queue.isEmpty()){
            builder.append((char) ('a' + queue.poll()));
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String s = "aab";
        String result = new ReorganizeString().reorganizeString(s);
        System.out.println(result);
    }
}
