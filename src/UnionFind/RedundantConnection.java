package UnionFind;

import java.util.Arrays;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 684
 * @date 2022/11/1 18:54
 */
public class RedundantConnection {
    int[] root;
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        root = new int[n + 1];
        //初始化，将每一个节点的指针指向自己，即每一个节点都是根
        for (int i = 1; i <= n; i++) {
            root[i] = i;
        }
        for (int[] edge : edges){
            int root0 = findRoot(edge[0]);
            int root1 = findRoot(edge[1]);
            //如果还没有对两个节点直接连接，但两个节点已在同一个图中（有相同的根），说明连接这两个节点就会形成环，返回两个节点构成的数组
            //反之，修改edge[0]的根为edge[1]的根
            if (root0 == root1){
                return new int[]{edge[0], edge[1]};
            }else {
                root[root0] = root1;
            }
        }
        return new int[]{-1, -1};
    }

    //寻找节点i的根，根的特征是自己指向自己
    public int findRoot(int i){
        while(root[i] != i){
            i = root[i];
        }
        return i;
    }

    public static void main(String[] args) {
        int[][] edges = {{1,2}, {1,3}, {2,3}};
        int[] result = new RedundantConnection().findRedundantConnection(edges);
        System.out.println(Arrays.toString(result));
    }
}
