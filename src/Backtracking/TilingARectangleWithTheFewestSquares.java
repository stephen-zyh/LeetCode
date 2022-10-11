package Backtracking;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1240
 * @date 2022/10/10 18:37
 */
public class TilingARectangleWithTheFewestSquares {
    private int result;
    int[][] visit;
    int min, max;
    public int tilingRectangle(int n, int m) {
        result = Math.max(m, n);    //数学归纳法可以证明最终结果不会大于边长的大者
        //确保m>=n
        if (n > m) {
            return tilingRectangle(m, n);
        }
        min = n;
        max = m;
        //visit用以记录哪些位置已经被地砖覆盖
        visit = new int[min][max];
        filling(0);
        return result;
    }

    private void filling(int steps){
        //一旦发现当前方案用到的瓷砖数大于等于之前找到的最小值便可结束DFS，从而提高效率
        if (steps >= result){
            return;
        }
        int row = -1, col = -1;
        for (int i = 0; i < min; i++) {
            for (int j = 0; j < max; j++) {
                //寻找一个没有铺瓷砖的、在铺了瓷砖的周围的点
                if (visit[i][j] == 0){
                    row = i;
                    col = j;
                    break;
                }
            }
            if (row >= 0)  break;
        }
        //如果没有地方可以继续铺砖，则更新最小值
        if (row == -1){
            result = Math.min(result, steps);
            return;
        }
        //为免越界，len的初值应该为Math.min(min - row, max - col)
        for (int len = Math.min(min - row, max - col); len >= 0; len--) {
            if (!canFill(row, col, len))    continue;
            //回溯
            doFill(row, col, len);
            filling(steps + 1);
            undoFill(row, col, len);
        }
    }

    //检查以(row, col)为起点的边长为len的正方形是否完全没被瓷砖覆盖
    private boolean canFill(int row, int col, int len){
        for (int i = row; i < row + len; i++) {
            for (int j = col; j < col + len; j++) {
                if (visit[i][j] == 1){
                    return false;
                }
            }
        }
        return true;
    }

    //将以(row, col)为起点的边长为len的正方形扑满瓷砖
    private void doFill(int row, int col, int len){
        for (int i = row; i < row + len; i++) {
            for (int j = col; j < col + len; j++) {
                visit[i][j] = 1;
            }
        }
    }

    //将以(row, col)为起点的边长为len的正方形中的瓷砖拆除
    private void undoFill(int row, int col, int len){
        for (int i = row; i < row + len; i++) {
            for (int j = col; j < col + len; j++) {
                visit[i][j] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int n = 5, m = 8;
        int result = new TilingARectangleWithTheFewestSquares().tilingRectangle(n, m);
        System.out.println(result);
    }
}
