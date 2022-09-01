package Heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 973
 * @date 2022/8/26 19:56
 */
public class KClosestPointToOrigin {
    public int[][] kClosest(int[][] points, int k) {
        int[][] result = new int[k][2];
        //大根堆
        PriorityQueue<int[]> queue = new PriorityQueue((Comparator<int[]>) (a, b) -> getDistance(b[0], b[1]) - getDistance(a[0], a[1]));
        for(int[] point : points){
            queue.offer(point);
            //保留最小的k个元素
            if (queue.size() > k){
                queue.poll();
            }
        }
        for (int i = result.length - 1; i >= 0; i--) {
            result[i][0] = queue.peek()[0];
            result[i][1] = queue.poll()[1];
        }
        return result;
    }
    private int getDistance(int x, int y){
        return x * x + y * y;
    }

    public static void main(String[] args) {
        int[][] points = {{3,3},{5,-1},{-2,4}};
        int k = 2;
        int[][] result = new KClosestPointToOrigin().kClosest(points, k);
        System.out.println(Arrays.deepToString(result));
    }
}
