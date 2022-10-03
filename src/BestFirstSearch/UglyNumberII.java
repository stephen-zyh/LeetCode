package BestFirstSearch;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 264，在Heap中也做过
 * @date 2022/10/3 11:47
 */
public class UglyNumberII {
    public int nthUglyNumber(int n) {
        HashSet<Long> set = new HashSet<>();    //记录已经加入过堆的元素
        PriorityQueue<Long> queue = new PriorityQueue<>();  //小根堆
        int[] factors = {2, 3, 5};
        queue.offer(1L);
        set.add(1L);
        int result = 0;
        for (int i = 0; i < n; i++) {
            long curr = queue.poll();   //根最小，连续取出n次根元素，最后一次取出的便是答案
            result = (int) curr;
            //将由curr产生的丑数也放入堆中
            for(int factor : factors){
                long nextNum = curr * factor;
                //避免重复放入
                if (!set.contains(nextNum)){
                    queue.offer(nextNum);
                    set.add(nextNum);
                }
            }
        }
        return result;
    }
}
