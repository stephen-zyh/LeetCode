package Heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1387
 * @date 2022/8/25 10:30
 */
public class SortIntegersByThePowerValue {
    public int getKth(int lo, int hi, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        //建立数与其权重的映射
        for (int i = lo; i <= hi; i++) {
            map.put(i, getWeight(i));
        }
        //大根堆比较规则为：若权重相等按元素降序排列，否则按照权重降序排列
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((e1, e2) ->
                e2.getValue().equals(e1.getValue()) ? e2.getKey() - e1.getKey() : e2.getValue() - e1.getValue()
        );
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            queue.offer(entry);
            //保证堆中始终保存遍历到的最小的k个元素
            if (queue.size() > k){
                queue.poll();
            }
        }
        return queue.peek().getKey();
    }

    //获取数的权重
    private int getWeight(int value){
        int weight = 0;
        while (value != 1){
            if ((value & 1) == 1){
                value = 3 * value + 1;
            }else {
                value = value / 2;
            }
            weight++;
        }
        return weight;
    }

    public static void main(String[] args) {
        int lo = 12, hi = 15, k = 2;
        int result = new SortIntegersByThePowerValue().getKth(lo, hi, k);
        System.out.println(result);
    }
}
