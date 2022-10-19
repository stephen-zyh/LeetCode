package DynamicProgramming2D_2DInput;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 85，Stack包中有使用单调栈的该题的另一解
 * @date 2022/10/19 8:24
 */
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[] dp = new int[col];    //dp矩阵由二维减为一维，虽然可读性有所下降，但空间复杂度降低
        int result = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //该节点值为1，则“直方图”高度加1，否则高度清零
                if (matrix[i][j] == '1'){
                    if (i == 0){
                        dp[j] = 1;
                    }else{
                        dp[j] = dp[j] + 1;
                    }
                    int height = dp[j];
                    //剪枝操作，若以0开始到索引j结束，以height为高的矩形面积都无法超越result，便没有继续迭代的必要了
                    if (height * (j + 1) <= result){
                        continue;
                    }
                    //当前矩形面积是当前底的长度与在底的索引范围内的最小高的乘积，result取矩形面积的最大值
                    for (int k = j; k >= 0 && dp[k] != 0; k--) {
                        height = Math.min(height, dp[k]);
                        result = Math.max(result, height * (j - k + 1));
                    }
                }else{
                    dp[j] = 0;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        int result = new MaximalRectangle().maximalRectangle(matrix);
        System.out.println(result);
    }
}
