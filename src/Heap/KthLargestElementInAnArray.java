package Heap;

import java.util.PriorityQueue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 215，参考leetcode 703借用堆进行解答，当然对数组排序后取倒数第k个元素也行得通
 * @date 2022/8/22 21:39
 */
public class KthLargestElementInAnArray {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        //保证小根堆里是遍历到的最大的k个元素
        for (int num : nums) {
            if (queue.size() < k) {
                queue.offer(num);
            } else if (queue.peek() < num) {
                queue.poll();
                queue.offer(num);
            }
        }
        return queue.peek();
    }
}
