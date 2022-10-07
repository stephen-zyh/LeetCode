package DepthFirstSearch;

import java.util.*;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1377
 * @date 2022/10/6 20:25
 */
public class FrogPositionAfterTSeconds {
    Map<Integer, Set<Integer>> graph = new HashMap<>();
    public double frogPosition(int n, int[][] edges, int t, int target) {
        //无边的特殊情况，青蛙只能在原地蹦跶，只需检查当前位置是否是要到的target即可
        if (edges == null || edges.length == 0){
            return target == 1 ? 1.0 : 0.0;
        }
        //建图
        for (int[] edge : edges){
            graph.putIfAbsent(edge[0], new HashSet<>());
            graph.putIfAbsent(edge[1], new HashSet<>());
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        //DFS
        return calculatePossibility(1, 1.0, t, target);
    }

    private double calculatePossibility(int current, double currPossibility, int timeLeft, int target){
        //base case1: 创业未半而中道崩殂，时间用完了还没到目的地
        if (timeLeft < 0){
            return 0.0;
        }
        //base case2: 到达目标节点，但只有剩下的时间为0或者虽然还有剩余时间但target是叶子节点（可以在原地蹦跶），才返回其概率
        if (current == target && (timeLeft == 0 || graph.get(current).size() == 0)){
            return currPossibility;
        }
        int childrenCount = graph.get(current).size();
        for (int next : graph.get(current)){
            //到达子节点next后斩断其和current的联系，保证从根到叶子节点的运动方向，避免DFS时回到current，造成死循环
            graph.get(next).remove(current);
            //计算从根节点经过next到target的概率
            double possibility = calculatePossibility(next, currPossibility / childrenCount, timeLeft - 1, target);
            //只要任何一个子节点到target的概率不为0就返回其概率，否则返回0
            if (possibility != 0.0){
                return possibility;
            }
        }
        return 0.0;
    }

    public static void main(String[] args) {
        int n = 7;
        int[][] edges = {{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}};
        int t = 2, target = 4;
        double result = new FrogPositionAfterTSeconds().frogPosition(n, edges, t, target);
        System.out.println(result);
    }
}
