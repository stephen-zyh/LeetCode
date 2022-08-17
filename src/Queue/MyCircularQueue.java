package Queue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 622
 * @date 2022/7/25 10:28
 */
public class MyCircularQueue {
    private final int[] queue;
    private int start, end;
    private  boolean flag;  //flag用于记录最后一次操作是入队还是出队，end==start时可以判断队列时满还是空

    public MyCircularQueue(int k) {
        queue = new int[k];
        start = 0;
        end = 0;
        flag = false;
    }

    public boolean enQueue(int value) {
        //start != end 或者最后一次操作是出队，表明队列未满
        if (end != start || !flag){
            queue[end] = value;
            end = (end + 1) % queue.length;
            flag = true;
            return true;
        }
        return false;
    }

    public boolean deQueue() {
        //start != end 或者最后一次操作是入队，表明队列非空
        if (end != start || flag){
            start = (start + 1) % queue.length;
            flag = false;
            return true;
        }
        return false;
    }

    public int Front() {
        if (isEmpty()){
            return -1;
        }
        return queue[start];
    }

    public int Rear() {
        if(isEmpty()){
            return -1;
        }
        return queue[(end -1 + queue.length) % queue.length];
    }

    public boolean isEmpty() {
        return end == start && !flag;
    }

    public boolean isFull() {
        return end == start && flag;
    }

    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
        System.out.println(circularQueue.enQueue(1)); // 返回 true
        System.out.println(circularQueue.enQueue(2)); // 返回 true
        System.out.println(circularQueue.enQueue(3)); // 返回 true
        System.out.println(circularQueue.enQueue(4)); // 返回 false，队列已满
        System.out.println(circularQueue.Rear()); // 返回 3
        System.out.println(circularQueue.isFull()); // 返回 true
        System.out.println(circularQueue.deQueue()); // 返回 true
        System.out.println(circularQueue.enQueue(4)); // 返回 true
        System.out.println(circularQueue.Rear()); // 返回 4
    }
}
