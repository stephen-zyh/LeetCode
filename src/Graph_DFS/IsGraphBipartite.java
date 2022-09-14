package Graph_DFS;

import java.util.Arrays;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 785
 * @date 2022/9/11 16:16
 */
public class IsGraphBipartite {
    public boolean isBipartite(int[][] graph) {
        int[] visit = new int[graph.length];
        Arrays.fill(visit, -1);
        //因为图未必是连通的，因此对于每一个节点，如果之前调用markNode之后没有访问到这个节点，
        //说明这个节点与之前的节点不在同一个节点中，需要再次调用markNode
        for (int i = 0; i < graph.length; i++) {
            if (visit[i] == -1 && !markNode(i, 0, visit, graph)){
                return false;
            }
        }
        return true;
    }
    private boolean markNode(int currNode, int currSign, int[] visit, int[][] graph){
        //base case, 当前节点已经标记过，查看标记是否与currSign相等，不相等说明不能二分
        if (visit[currNode] != -1){
            return visit[currNode] == currSign;
        }
        visit[currNode] = currSign;
        for(int neighbor : graph[currNode]){
            //递归调用markNode,将当前节点的邻居节点作相反的标记并判断是否能够二分
            if (!markNode(neighbor, 1 - currSign, visit, graph)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        int[][] graph = {{1,2,3},{0,2},{0,1,3},{0,2}};
        int[][] graph = {{},{2,4,6},{1,4,8,9},{7,8},{1,2,8,9},{6,9},{1,5,7,8,9},{3,6,9},{2,3,4,6,9},{2,4,5,6,7,8}};
        boolean result = new IsGraphBipartite().isBipartite(graph);
        System.out.println(result);
    }
}
