package Graph_BestFS;

import java.util.PriorityQueue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1102
 * @date 2022/9/6 11:35
 */
public class PathWithMaximumMinimumValue {
    public int maximumMinimumPath(int[][] matrix) {
        boolean[][] visit = new boolean[matrix.length][matrix[0].length];
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[2] - a[2]);    //大根堆
        queue.offer(new int[]{0, 0, matrix[0][0]}); //x, y, 以及经过当前点的路径的最大的最小值
        int[] directions = {-1, 0, 1, 0, -1};
        while (!queue.isEmpty()){
            int[] curr = queue.poll();
            if (curr[0] == matrix.length - 1 && curr[1] == matrix[0].length -1){
                return curr[2]; //到达终点，返回结果
            }
            visit[curr[0]][curr[1]] = true;
            //更新当前点四周的代价
            for (int i = 0; i < 4; i++) {
                int x = curr[0] + directions[i];
                int y = curr[1] + directions[i + 1];

                if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && !visit[x][y]){
                    /*
                    同一个点可能多次入堆，但按比较规则的大者优先出堆并对四周节点更新，小者出堆后更新得到的四周节点也更小，
                    因此四周节点仍是按照比较规则大者先出堆，然后visit设为true，也就是说小者没有机会更新周围节点值，
                    可能在堆还未空的时候就已经找到结果并返回了
                     */
                    queue.offer(new int[]{x, y, Math.min(matrix[x][y], curr[2])});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] matrix = {{5,4,5},{1,2,6},{7,4,6}};
        int result = new PathWithMaximumMinimumValue().maximumMinimumPath(matrix);
        System.out.println(result);
    }
}
