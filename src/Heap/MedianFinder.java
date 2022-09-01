package Heap;

import java.util.PriorityQueue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 295
 * @date 2022/8/30 9:55
 */
public class MedianFinder {
    private final PriorityQueue<Integer> minHeap;
    private final PriorityQueue<Integer> maxHeap;
    public MedianFinder() {
        minHeap = new PriorityQueue<>();    //小根堆，存大的一半
        maxHeap = new PriorityQueue<>((a, b) -> b - a); //大根堆，存小的一半
    }

    public void addNum(int num) {
        //1.当小根堆为空，优先加入小根堆
        //2.当前值比小根堆堆顶元素更小，加入大根堆
        //3.反之加入小根堆
        if (minHeap.isEmpty()){
            minHeap.offer(num);
        }else if (num < minHeap.peek()){
            maxHeap.offer(num);
        }else {
            minHeap.offer(num);
        }
        //对两堆大小进行平衡
        int a = minHeap.size(), b = maxHeap.size();
        if (a - b > 1){
            maxHeap.offer(minHeap.poll());
        }else if (b - a > 1){
            minHeap.offer(maxHeap.poll());
        }
    }

    public double findMedian() {
        //两堆大小相等，则返回两堆堆顶的平均；否则返回尺寸更大的堆的堆顶
        if (maxHeap.size() == minHeap.size()){
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        }else if (maxHeap.size() < minHeap.size()){
            return minHeap.peek();
        }
        return maxHeap.peek();
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }
}
