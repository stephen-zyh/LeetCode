package BestFirstSearch;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 871
 * @date 2022/10/3 15:39
 */
public class MinimumNumberOfRefuelingStops {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        if (stations == null || stations.length == 0){
            return startFuel >= target ? 0 : -1;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(a -> -a));   //大根堆
        int currentFuel = startFuel;
        int index = 0, step = 0;
        while (currentFuel < target){
            //当遇到加油站且燃料足够的时候，不添加燃料，而是将燃料加入堆中
            if (index < stations.length && currentFuel >= stations[index][0]){
                queue.offer(stations[index][1]);
                index++;
            }else {
                //燃料不够，从堆中添加分量最大的燃料，如果没有燃料，说明无法继续走下去，返回-1
                if (!queue.isEmpty()){
                    currentFuel += queue.poll();
                    step++;
                }else {
                    return -1;
                }
            }
        }
        return step;
    }

    public static void main(String[] args) {
        int target = 100;
        int startFuel = 50;
        int[][] stations = {{25,25},{50,50}};
        int result = new MinimumNumberOfRefuelingStops().minRefuelStops(target, startFuel, stations);
        System.out.println(result);
    }
}
