package Graph_DFS;

import java.util.*;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1059，累了，用DFS居然一直报超时，也许可以试试拓扑排序？
 * @date 2022/9/13 7:59
 */
public class AllPathsFromSourceLeadToDestination {
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        //构建邻接表
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges){
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.get(edge[0]).add(edge[1]);
        }
        Set<Integer> visit = new HashSet<>();
        if (graph.containsKey(destination)){
            return false;
        }
        return isToDestination(graph, source, destination, visit);
    }
    private boolean isToDestination(Map<Integer, List<Integer>> graph, int current, int destination, Set<Integer> visit){
        //base case
        if (!graph.containsKey(current)){
            return current == destination;
        }
        //回溯
        visit.add(current);
        for(int neighbor : graph.get(current)){
            if (visit.contains(neighbor) || !isToDestination(graph, neighbor, destination, visit)){
                return false;
            }
        }
        visit.remove(current);
        return true;
    }

    public static void main(String[] args) {
//        int n = 3;
//        int[][] edges = {{0,1},{0,2}};
//        int source = 0, destination = 2;

//        int n = 4;
//        int[][] edges = {{0,1},{0,3},{1,2},{2,1}};
//        int source = 0, destination = 3;

//        int n = 4;
//        int[][] edges = {{0,1},{0,2},{1,3},{2,3}};
//        int source = 0, destination = 3;

//        int n = 3;
//        int[][] edges = {{0,1},{1,1},{1,2}};
//        int source = 0, destination = 2;

//        int n = 2;
//        int[][] edges = {{0,1},{1,1}};
//        int source = 0, destination = 1;

        int n = 5;
        int[][] edges = {{0,1},{0,2},{0,3},{0,3},{1,2},{1,3},{1,4},{2,3},{2,4},{3,4}};
        int source = 0, destination = 4;
        boolean result = new AllPathsFromSourceLeadToDestination().leadsToDestination(n, edges, source, destination);
        System.out.println(result);
    }
}
