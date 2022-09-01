package Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 818
 * @date 2022/8/31 17:31
 */
public class RaceCar {
    public int racecar(int target) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        queue.offer(new int[]{0, 0, 1});
        int result;
        while (true){
            int[] curr = queue.poll();
            int step = curr[0];
            int position = curr[1];
            int speed = curr[2];
            //position == target时记录结果并退出循环
            if (position == target){
                result = step;
                break;
            }
            if (position + speed > target && speed > 0){
                queue.offer(new int[]{step + 1, position, -1}); //转向，位置不变，指令长度加1
            }
            if (position + speed < target && speed < 0){
                queue.offer(new int[]{step + 1, position, 1});  //转向，位置不变，指令长度加1
            }
            queue.offer(new int[]{step + 1, position + speed, speed * 2});  //直接加速
        }
        return result;
    }

    public static void main(String[] args) {
        int result = new RaceCar().racecar(6);
        System.out.println(result);
    }
}
