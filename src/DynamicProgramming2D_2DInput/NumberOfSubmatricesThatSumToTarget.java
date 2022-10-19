package DynamicProgramming2D_2DInput;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1074
 * @date 2022/10/19 18:36
 */
public class NumberOfSubmatricesThatSumToTarget {
    int[][] dp;
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        dp = new int[row + 1][col + 1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                //求前缀和，dp[i][j]表示前i行j列构成的矩形的面积
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
        int result = 0;
        //暴力求解
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int k = i; k < row; k++) {
                    for (int l = j; l < col; l++) {
                        if (sumRegion(i, j, k ,l) == target){
                            result++;
                        }
                    }
                }
            }
        }
        return result;
    }

    private int sumRegion(int row1, int col1, int row2, int col2) {
        //注意：右下角的索引为(row2,col2)，但dp[row2 + 1][col2 + 1]才表示前row2行col2列构成的矩形
        //由于dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1]多减去了一个dp[row1][col1]，
        //因此计算围成的面积时要将dp[row1][col1]加回来
        return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
    }

    public static void main(String[] args) {
        int[][] matrix = {{0,1,0},{1,1,1},{0,1,0}};
        int target = 0;
        int result = new NumberOfSubmatricesThatSumToTarget().numSubmatrixSumTarget(matrix, target);
        System.out.println(result);
    }
}
