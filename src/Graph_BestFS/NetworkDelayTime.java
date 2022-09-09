package Graph_BestFS;

import java.util.Arrays;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 743
 * @date 2022/9/6 10:47
 */
public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        //建图
        int[][] graph = new int[n][n];
        for (int i = 0; i < graph.length; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE / 2);
        }
        for (int i = 0; i < times.length; i++) {
            int source = times[i][0] - 1;   //n个节点从1开始编号，因此存入矩阵时要减1
            int target = times[i][1] - 1;
            graph[source][target] = times[i][2];
        }
        boolean[] visit = new boolean[n];   //标记是否访问
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE / 2);   //值填为Integer.MAX_VALUE / 2是为防止两个值相加时溢出
        distance[k - 1] = 0;
        for (int i = 0; i < n; i++) {
            int curr = -1;
            for (int j = 0; j < n; j++) {
                //找到当前未访问的代价最小节点
                if (!visit[j] && (curr == -1 || distance[j] < distance[curr])){
                    curr = j;
                }
            }
            //将找到的代价最小节点状态设置为已访问，并以此节点更新其余节点的代价
            visit[curr] = true;

            for (int j = 0; j < n; j++) {
                distance[j] = Math.min(distance[j], distance[curr] + graph[curr][j]);
            }
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (distance[i] == Integer.MAX_VALUE / 2){
                return -1;  //有不可到达的节点，返回-1
            }
            result = Math.max(result, distance[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
        int n = 4, k = 2;
        int result = new NetworkDelayTime().networkDelayTime(times, n, k);
        System.out.println(result);
    }
}
