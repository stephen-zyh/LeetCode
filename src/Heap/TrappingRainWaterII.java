package Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 407
 * @date 2022/8/31 10:44
 */
public class TrappingRainWaterII {
    public int trapRainWater(int[][] heightMap) {
        int row = heightMap.length;
        int col = heightMap[0].length;
        //col <= 2 || row <= 2时不能构成盛水的容器
        if (col <= 2 || row <= 2){
            return 0;
        }
        int result = 0;
        boolean[][] visit = new boolean[row][col];
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        //记录容器边缘高度，放入小根堆中，并将相应位置的visit设置为true
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || i == row - 1 || j == 0 || j == col - 1){
                    queue.offer(new int[]{i * col + j, heightMap[i][j]});
                    visit[i][j] = true;
                }
            }
        }
        int[] dirs = {-1,0,1,0,-1};
        //从已经遍历过的容器壁中依次取最矮的，并将其与在容器内的未遍历的上下左右四个位置进行比较
        while (!queue.isEmpty()){
            int[] curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = curr[0] / col + dirs[i];
                int y = curr[0] % col + dirs[i + 1];
                if (x > 0 && x < row && y > 0 && y < col && ! visit[x][y]){
                    if (curr[1] > heightMap[x][y]){
                        //容器壁更高，可以盛水，heightMap[x][y]设置为curr[1]并作为新的容器边缘加入到堆中
                        result += curr[1] - heightMap[x][y];
                        queue.offer(new int[]{x * col + y, curr[1]});
                    }else {
                        //容器壁更低，不可以盛水，heightMap[x][y]直接作为新的容器边缘加入到堆中
                        queue.offer(new int[]{x * col + y, heightMap[x][y]});
                    }
                    visit[x][y] = true; //将该处设置为已遍历
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] heightMap = {{3,3,3,3,3},{3,2,2,2,3},{3,2,1,2,3},{3,2,2,2,3},{3,3,3,3,3}};
        int result = new TrappingRainWaterII().trapRainWater(heightMap);
        System.out.println(result);
    }
}
