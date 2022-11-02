package UnionFind;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 323
 * @date 2022/11/1 16:58
 */
public class NumberOfConnectedComponentsInAnUndirectedGraph {
    int[] root;
    public int countComponents(int n, int[][] edges) {
        root = new int[n];
        //初始化，将每一个节点的指针指向自己，即每一个节点都是根
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }
        //节点i和节点j相连，将节点i的根指向节点j的根
        for (int[] edge : edges){
            unionFunction(edge[0], edge[1]);
        }
        int result = 0;
        //如果两个节点的根相同，它们必属于同一个图（网络），因此只需要统计不同根的个数即可
        for (int i = 0; i < n; i++) {
            if (root[i] == i){
                result++;
            }
        }
        return result;
    }

    public void unionFunction(int i, int j){
        if (i < j) {
            int rootI = findRoot(i);
            int rootJ = findRoot(j);
            //将节点i的根指向节点j的根
            if (rootI != rootJ){
                root[rootI] = rootJ;
            }
        } else {
            unionFunction(j, i);
        }
    }

    public int findRoot(int i){
        //寻找节点i的根，根的特征是自己指向自己
        if (i == root[i]){
            return i;
        }
        return findRoot(root[i]);
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        int result = new NumberOfConnectedComponentsInAnUndirectedGraph().countComponents(n, edges);
        System.out.println(result);
    }
}
