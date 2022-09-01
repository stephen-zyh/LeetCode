package Heap;

import java.util.*;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 692
 * @date 2022/8/24 22:06
 */
public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>(); //记录单词出现的次数
        for (String word : words){
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        //单词出现次数相等则按单词的字典序排序，否则按照出现次数排序
        PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>((e1, e2) ->
                e1.getValue().equals(e2.getValue()) ? e2.getKey().compareTo(e1.getKey()) : e1.getValue() - e2.getValue()
        );
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            queue.offer(entry);
            //堆中只保留前k个元素
            if (queue.size() > k){
                queue.poll();
            }
        }
        //记录结果到列表中
        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()){
            result.add(0, queue.poll().getKey());
        }
        return result;
    }

    public static void main(String[] args) {
        String[] words = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        int k = 4;
        List<String> result = new TopKFrequentWords().topKFrequent(words, k);
        System.out.println(result);
    }
}
