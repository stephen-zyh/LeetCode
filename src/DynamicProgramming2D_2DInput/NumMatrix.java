package DynamicProgramming2D_2DInput;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 304
 * @date 2022/10/18 17:31
 */
public class NumMatrix {
    int[][] dp;
    public NumMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        dp = new int[row + 1][col + 1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                //求前缀和，dp[i][j]表示前i行j列构成的矩形的面积
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        //注意：右下角的索引为(row2,col2)，但dp[row2 + 1][col2 + 1]才表示前row2行col2列构成的矩形
        //由于dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1]多减去了一个dp[row1][col1]，
        //因此计算围成的面积时要将dp[row1][col1]加回来
        return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
    }

    public static void main(String[] args) {
        NumMatrix numMatrix = new NumMatrix(new int[][]{{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}});
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3)); // return 8 (红色矩形框的元素总和)
        System.out.println(numMatrix.sumRegion(1, 1, 2, 2)); // return 11 (绿色矩形框的元素总和)
        System.out.println(numMatrix.sumRegion(1, 2, 2, 4)); // return 12 (蓝色矩形框的元素总和)
    }
}
