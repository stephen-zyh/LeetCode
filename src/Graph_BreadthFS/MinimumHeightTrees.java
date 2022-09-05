package Graph_BreadthFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 310，每次剪切掉树的最外层节点（度为1），最后留下的即是所求节点
 * @date 2022/9/2 11:14
 */
public class MinimumHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> result = new ArrayList<>();
        //如果只有一个节点，则以该节点为根节点构成的树有最小深度
        if (n == 1){
            result.add(0);
            return result;
        }
        int[] degree = new int[n];  //记录每个节点的度
        ArrayList<ArrayList<Integer>> neighbors = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            neighbors.add(new ArrayList<>());   //每个节点的邻接节点用一个列表存储
        }
        for(int[] edge : edges){
            degree[edge[0]]++;
            degree[edge[1]]++;
            neighbors.get(edge[0]).add(edge[1]);
            neighbors.get(edge[1]).add(edge[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        //叶子节点入队
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1){
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()){
            int size = queue.size();
            result = new ArrayList<>(); //每次while循环result都会被更新
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                result.add(curr);
                ArrayList<Integer> list = neighbors.get(curr);
                for(int node : list){
                    degree[node]--; //相应的邻居节点度减一
                    if (degree[node] == 1){
                        queue.offer(node);  //剪切掉该叶子节点后其邻居节点成为新的叶子节点，则将其入队
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] edges = {{3,0},{3,1},{3,2},{3,4},{5,4}};
        int n = 6;
        List<Integer> minHeightTrees = new MinimumHeightTrees().findMinHeightTrees(n, edges);
        System.out.println(minHeightTrees);
    }
}
