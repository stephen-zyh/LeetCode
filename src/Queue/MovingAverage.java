package Queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 346
 * @date 2022/7/24 22:38
 */
public class MovingAverage {
    private Queue<Integer> queue;
    private int size = 0;
    private double sum = 0;

    public MovingAverage(int size) {
        queue = new LinkedList<>();
        this.size = size;
        sum = 0;
    }

    public double next(int val) {
        if (queue.size() < size){
            queue.offer(val);
            sum += val;
            return sum / queue.size();
        }else {
            //队列已满，先出队再入队
            sum -= queue.poll();
            queue.offer(val);
            sum += val;
            return sum / size;
        }
    }

    public static void main(String[] args) {
        MovingAverage movingAverage = new MovingAverage(3);
        System.out.println(movingAverage.next(1));
        System.out.println(movingAverage.next(10));
        System.out.println(movingAverage.next(3));
        System.out.println(movingAverage.next(5));
    }
}
