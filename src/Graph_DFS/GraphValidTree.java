package Graph_DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 261，本题的思考角度很巧妙！
 * @date 2022/9/12 16:34
 */
public class GraphValidTree {
    int count = 0;
    public boolean validTree(int n, int[][] edges) {
        //n个节点的树必然有n-1条边，否则就不是树
        if (edges.length != n - 1){
            return false;
        }
        //建立邻接表
        List<List<Integer>> table = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            table.add(new ArrayList<>());
        }
        for (int[] edge : edges){
            table.get(edge[0]).add(edge[1]);
            table.get(edge[1]).add(edge[0]);
        }
        boolean[] visit = new boolean[n];
        //n-1条边要包含所有n个节点，图才是树
        countNode(table, 0, visit);
        return count == n;
    }
    private void countNode(List<List<Integer>> table, int currNode, boolean[] visit){
        //base case，已访问节点不再继续访问
        if (visit[currNode]){
            return;
        }
        //若是尚未访问，节点计数加1
        visit[currNode] = true;
        count++;
        //继续访问邻居节点
        for (int neighbor : table.get(currNode)){
            countNode(table, neighbor, visit);
        }
    }

    public static void main(String[] args) {
        int n = 5;
//        int[][] edges = {{0,1}, {0,2}, {0,3}, {1,4}};
        int[][] edges = {{0,1}, {1,2}, {2,3}, {1,3}, {1,4}};
        boolean result = new GraphValidTree().validTree(n, edges);
        System.out.println(result);
    }
}
