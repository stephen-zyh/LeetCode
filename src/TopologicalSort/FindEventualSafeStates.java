package TopologicalSort;

import java.util.*;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 802
 * @date 2022/11/2 17:21
 */
public class FindEventualSafeStates {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        Map<Integer, List<Integer>> map = new HashMap<>();  //节点和指向该节点的列表的映射
        Queue<Integer> queue = new LinkedList<>();
        int length = graph.length;
        int[] outDegree = new int[length];  //记录每个节点的出度
        for (int i = 0; i < length; i++) {
            map.put(i, new ArrayList<>());
            outDegree[i] = graph[i].length;
            if (outDegree[i] == 0){
                queue.offer(i);
            }
        }
        //建立邻接表
        for (int i = 0; i < length; i++) {
            for (int neighbor : graph[i]){
                map.get(neighbor).add(i);
            }
        }
        List<Integer> result = new ArrayList<>();
        //BFS，维护一个队列，队列里是出度为0的节点
        while (!queue.isEmpty()){
            int curr = queue.poll();
            //删除出度为0的节点的同时更新其各个neighbor的出度，如果某个neighbor的出度为0，继续将其入队
            for (int neighbor : map.get(curr)){
                outDegree[neighbor]--;
                if (outDegree[neighbor] == 0){
                    queue.offer(neighbor);
                }
            }
            result.add(curr);
        }
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        int[][] graph = {{1,2},{2,3},{5},{0},{5},{},{}};
        List<Integer> result = new FindEventualSafeStates().eventualSafeNodes(graph);
        System.out.println(result);
    }
}
