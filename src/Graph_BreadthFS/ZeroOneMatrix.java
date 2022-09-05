package Graph_BreadthFS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 542
 * @date 2022/9/3 11:09
 */
public class ZeroOneMatrix {
    public int[][] updateMatrix(int[][] mat) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[mat.length][mat[0].length];
        //遍历找到本来就是0的位置，后续以这些位置为起点进行BFS
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0){
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        int[] dirs = {-1,0,1,0,-1};
        while (!queue.isEmpty()){
            int[] currPoint = queue.poll();
            //对currPoint周围的、在矩阵范围内且未被访问的节点遍历
            for (int i = 0; i < 4; i++) {
                int x = currPoint[0] + dirs[i];
                int y = currPoint[1] + dirs[i + 1];
                if (x >= 0 && x < mat.length && y >= 0 && y < mat[0].length && !visited[x][y]){
                    mat[x][y] = mat[currPoint[0]][currPoint[1]] + 1;    //求得与currPoint的距离
                    queue.offer(new int[]{x, y});   //将已遍历的节点作为新的边界
                    visited[x][y] = true;   //状态设置为true，表示已遍历
                }
            }
        }
        return mat;
    }

    public static void main(String[] args) {
        int[][] mat = {{0,0,0},{0,1,0},{1,1,1}};
        int[][] matrix = new ZeroOneMatrix().updateMatrix(mat);
        System.out.println(Arrays.deepToString(matrix));
    }
}
