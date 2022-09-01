package Heap;

import java.util.PriorityQueue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 378
 * @date 2022/8/27 10:55
 */
public class KthSmallestElementInASortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (a, b) -> b - a); //大根堆
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                //由于每一行都是升序排列的，满足queue.size() == k && matrix[i][j] > queue.peek()即可跳出循环
                if (queue.size() == k && matrix[i][j] > queue.peek()){
                    break;
                }
                if (queue.size() == k){
                    queue.poll();   //弹出最大的元素，使得堆中保存的始终是最小的k个元素
                }
                queue.offer(matrix[i][j]);
            }
        }
        return queue.peek();
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,5,9},{10,11,13},{12,13,15}};
        int k = 8;
        int result = new KthSmallestElementInASortedMatrix().kthSmallest(matrix, k);
        System.out.println(result);
    }
}
