package Heap;

import java.util.*;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 373
 * @date 2022/8/26 10:07
 */
public class FindKPairsWithSmallestSums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        //大顶堆
        PriorityQueue<List<Integer>> queue = new PriorityQueue<>((a, b) -> b.get(0) + b.get(1) - (a.get(0) + a.get(1)));

        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            for (int j = 0; j < Math.min(nums2.length, k); j++) {
                //由于nums1和nums2都是递增的，可作优化：nums1[i] + nums2[j]大于peek的值时即可结束内层循环
                if (queue.size() == k && nums1[i] + nums2[j] > queue.peek().get(0) + queue.peek().get(1)){
                    break;
                }
                if (queue.size() == k){
                    queue.poll();
                }
                List<Integer> list = new ArrayList<>();
                list.add(nums1[i]);
                list.add(nums2[j]);
                queue.offer(list);
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        while (!queue.isEmpty()){
            //由于是大顶堆，每次弹出的元素需要添加到列表头部
            result.add(0, queue.poll());
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,7,11}, nums2 = {2,4,6};
        int k = 3;
        List<List<Integer>> result = new FindKPairsWithSmallestSums().kSmallestPairs(nums1, nums2, k);
        System.out.println(result);
    }
}
