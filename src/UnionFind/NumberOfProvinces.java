package UnionFind;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 547
 * @date 2022/11/1 10:57
 */
public class NumberOfProvinces {
    int[] root;
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        root = new int[n];
        //初始化，将每一个节点的指针指向自己，即每一个节点都是根
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }
        //节点i和节点j相连，将节点i的根指向节点j的根
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (isConnected[i][j] == 1){
                    unionFunction(i, j);
                }
            }
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

    //寻找节点i的根，根的特征是自己指向自己
    public int findRoot(int i){
        while (root[i] != i){
            i = root[i];
        }
        return i;
    }
    public void unionFunction(int i, int j){
        int rootI = findRoot(i);
        int rootJ = findRoot(j);
        //将节点i的根指向节点j的根
        if (rootI != rootJ){
            root[rootI] = rootJ;
        }
    }

    public static void main(String[] args) {
        int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
        int result = new NumberOfProvinces().findCircleNum(isConnected);
        System.out.println(result);
    }
}
