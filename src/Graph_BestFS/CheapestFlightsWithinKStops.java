package Graph_BestFS;

import java.util.Arrays;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 787
 * @date 2022/9/8 8:29
 */
public class CheapestFlightsWithinKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int INF = 0x3f3f3f3f;
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], INF);
        }
        //建图
        for (int[] flight : flights) {
            graph[flight[0]][flight[1]] = flight[2];
        }

        int[] distance = new int[n];
        Arrays.fill(distance, INF);
        distance[src] = 0;  //源节点到自己的距离为0

        int[] clone;
        while (k-- >= 0){
            //需要注意的是，在遍历所有的“点对/边”进行松弛操作前，需要先对 dist 进行备份，
            // 否则会出现「本次松弛操作所使用到的边，也是在同一次迭代所更新的」，从而不满足边数限制的要求。
            //举个 🌰，例如本次松弛操作使用了从 a 到 b 的当前最短距离来更新 dist[b]，
            // 直接使用 dist[a] 的话，不能确保 dist[a] 不是在同一次迭代中所更新，
            // 如果 dist[a] 是同一次迭代所更新的话，那么使用的边数将会大于 k 条。
            clone = distance.clone();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distance[j] > clone[i] + graph[i][j]){
                        distance[j] = clone[i] + graph[i][j];
                    }
                }
            }
        }
        return distance[dst] >= INF ? -1 : distance[dst];
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] flights = {{0,1,100},{1,2,100},{0,2,500}};
        int src = 0, dst = 2, k = 1;
        int cheapestPrice = new CheapestFlightsWithinKStops().findCheapestPrice(n, flights, src, dst, k);
        System.out.println(cheapestPrice);
    }
}
