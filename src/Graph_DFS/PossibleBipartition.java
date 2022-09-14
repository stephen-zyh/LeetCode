package Graph_DFS;

import java.util.*;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 886, 与leetcode 785类似
 * @date 2022/9/11 21:26
 */
public class PossibleBipartition {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        int[] color = new int[n];
        //建立邻接表
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] dislike : dislikes) {
            map.putIfAbsent(dislike[0], new HashSet<>());
            map.putIfAbsent(dislike[1], new HashSet<>());
            map.get(dislike[0]).add(dislike[1]);
            map.get(dislike[1]).add(dislike[0]);
        }
        //因为图未必是连通的，因此对于每一个节点，如果之前调用markNode之后没有访问到这个节点，
        //说明这个节点与之前的节点不在同一个节点中，需要再次调用markNode
        for (int i = 0; i < n; i++) {
            //节点编号为1 ~ n，因此调用函数时传入i+1
            if (color[i] == 0 && !markNode(i + 1, 1, color, map)){
                return false;
            }
        }
        return true;
    }
    private boolean markNode(int currNode, int currSign, int[] color, Map<Integer, Set<Integer>> map){
        //base case, 当前节点已经标记过，查看标记是否与currSign相等，不相等说明不能二分
        if (color[currNode - 1] != 0){
            return color[currNode - 1] == currSign;
        }
        color[currNode - 1] = currSign;
        //没有不喜欢的人，返回true
        if (map.get(currNode) == null){
            return true;
        }
        //递归调用markNode,将当前节点的邻居节点作相反的标记并判断是否能够二分
        for(int neighbor : map.get(currNode)){
            if (!markNode(neighbor, -currSign, color, map)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        int n = 4;
//        int[][] dislikes = {{1,2},{1,3},{2,4}};
        int n = 3;
        int[][] dislikes = {{1,2},{1,3},{2,3}};
        boolean result = new PossibleBipartition().possibleBipartition(n, dislikes);
        System.out.println(result);
    }
}
