package Graph_DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 417，由边缘向中心反向遍历更新，避免重复遍历
 * @date 2022/9/13 10:28
 */
public class PacificAtlanticWaterFlow {
    int[][] heights;
    int[] directions = {-1,0,1,0,-1};
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        this.heights = heights;
        boolean[][] pacific = new boolean[heights.length][heights[0].length];
        boolean[][] atlantic = new boolean[heights.length][heights[0].length];
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                //从紧挨太平洋的点开始标记能流入海洋的点
                if (i == 0 || j == 0){
                    markNode(i, j, pacific);
                }
                //从紧挨大西洋的点开始标记能流入海洋的点
                if (i == heights.length - 1 || j == heights[0].length - 1){
                    markNode(i, j, atlantic);
                }
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                //将同时能够到达太平洋和大西洋的点加入结果中
                if (pacific[i][j] && atlantic[i][j]){
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    result.add(list);
                }
            }
        }
        return result;
    }

    private void markNode(int abscissa, int ordinate, boolean[][] matrix){
        //base case 已标记的点不再标记
        if (matrix[abscissa][ordinate]){
            return;
        }
        matrix[abscissa][ordinate] = true;
        //遍历当前点四周的点
        for (int i = 0; i < 4; i++) {
            int x = abscissa + directions[i];
            int y = ordinate + directions[i + 1];
            //矩阵外的点不予处理
            if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length){
                continue;
            }
            //周围未遍历的点只有比当前能流入海洋的点值更大（即陆地更高），才有可能通过当前点流入海洋，故对其进行DFS
            if (heights[x][y] >= heights[abscissa][ordinate]){
                markNode(x, y, matrix);
            }
        }
    }

    public static void main(String[] args) {
        int[][] heights = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        List<List<Integer>> result = new PacificAtlanticWaterFlow().pacificAtlantic(heights);
        System.out.println(result);
    }
}
