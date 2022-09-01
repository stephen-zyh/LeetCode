package Heap;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 264，除了借用堆进行处理外，也可以使用三指针求解以降低时空复杂度
 * @date 2022/8/27 11:22
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

    //由于遍历时放入堆中会消耗更多时间，最后堆里面仍然放了很多元素未使用，因此可以使用三指针降低时空复杂度
    public int nthUglyNumber2(int n) {
        int[] result = new int[n];
        //p2, p3, p5分别用于记录2,3,5应该乘以的数的下标
        int p2 = 0, p3 = 0, p5 = 0;
        result[0] = 1;
        for(int i = 1; i < n; i++){
            result[i] = Math.min(2 * result[p2], Math.min(3 * result[p3], 5 * result[p5]));
            //不是if-else，而是只要相等就要移动指针
            if(result[i] == 2 * result[p2]) p2++;
            if(result[i] == 3 * result[p3]) p3++;
            if(result[i] == 5 * result[p5]) p5++;
        }
        return result[n - 1];
    }

    public static void main(String[] args) {
        int result1 = new UglyNumberII().nthUglyNumber(1690);
        int result2 = new UglyNumberII().nthUglyNumber2(1690);
        System.out.println(result1);
        System.out.println(result2);
    }
}
