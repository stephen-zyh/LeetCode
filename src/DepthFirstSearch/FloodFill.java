package DepthFirstSearch;

import java.util.Arrays;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 733
 * @date 2022/10/4 9:50
 */
public class FloodFill {
    int[] directions = {-1, 0, 1, 0, -1};
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        boolean[][] visit = new boolean[image.length][image[0].length];
        staining(image, sr, sc, image[sr][sc], color, visit);
        return image;
    }

    private void staining(int[][] image, int row, int col, int oldColor, int newColor, boolean[][] visit){
        image[row][col] = newColor; //改为要求渲染的颜色
        visit[row][col] = true;
        //更新四周相连位置的像素值
        for (int i = 0; i < 4; i++) {
            int x = row + directions[i];
            int y = col + directions[i + 1];
            //四周在矩阵中尚未遍历且颜色和起点像素原来的像素值相等的，才有机会被渲染
            if (x >= 0 && x < image.length && y >= 0 && y < image[0].length && !visit[x][y] && image[x][y] == oldColor){
                staining(image, x, y, oldColor, newColor, visit);
            }
        }
    }

    public static void main(String[] args) {
        int[][] image = {{0,0,0},{0,0,0}};
        int sr = 0, sc = 0, newColor = 2;
        new FloodFill().floodFill(image, sr, sc, newColor);
        System.out.println(Arrays.deepToString(image));
    }
}
