package BestFirstSearch;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 818，Heap中也有该题的解，在此不做更多解释
 * @date 2022/10/3 12:55
 */
public class RaceCar {
    public int racecar(int target) {
        int result = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        queue.offer(new int[]{0, 0, 1});
        while (!queue.isEmpty()){
            int[] curr = queue.poll();
            int step = curr[0];
            int position = curr[1];
            int speed = curr[2];
            if (position == target){
                result = step;
                break;
            }
            if (position + speed > target && speed > 0){
                queue.offer(new int[]{step + 1, position, -1});
            }
            if (position + speed < target && speed < 0){
                queue.offer(new int[]{step + 1, position, 1});
            }
            queue.offer(new int[]{step + 1, position + speed, speed * 2});
        }
        return result;
    }

    public static void main(String[] args) {
        int result = new RaceCar().racecar(6);
        System.out.println(result);
    }
}
