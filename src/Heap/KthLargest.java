package Heap;

import java.util.PriorityQueue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 703
 * @date 2022/8/21 14:21
 */
public class KthLargest {
    private final PriorityQueue<Integer> queue; //小根堆
    private final int k;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        queue = new PriorityQueue<>(k);
        for (int i : nums){
            add(i);
        }
    }

    public int add(int val) {
        //堆中元素不足k个，直接添加到堆中即可
        //堆中元素已经有k个了，判断堆顶元素与当前元素大小关系，保证堆中元素是已遍历的元素中最大的k个
        if (queue.size() < k){
            queue.offer(val);
        }else if (queue.peek() < val){
            queue.poll();
            queue.offer(val);
        }
        return queue.peek();
    }

    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println(kthLargest.add(3));   // return 4
        System.out.println(kthLargest.add(5));   // return 5
        System.out.println(kthLargest.add(10));  // return 5
        System.out.println(kthLargest.add(9));   // return 8
        System.out.println(kthLargest.add(4));   // return 8
    }
}
