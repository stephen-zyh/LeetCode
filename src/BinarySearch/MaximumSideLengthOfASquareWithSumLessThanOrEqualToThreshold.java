package BinarySearch;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1292
 * @date 2022/9/19 18:52
 */
public class MaximumSideLengthOfASquareWithSumLessThanOrEqualToThreshold {
    public int maxSideLength(int[][] mat, int threshold) {
        int row = mat.length;
        int col = mat[0].length;
        int[][] prefixSum = new int[row + 1][col + 1];
        //求前缀和
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        int lower = 0, upper = Math.min(col, row);
        //二分法确定正方形边长
        while (lower < upper){
            int middle = lower + (upper - lower + 1) / 2;
            if (check(middle, prefixSum, threshold)){
                lower = middle;
            }else {
                upper = middle - 1;
            }
        }
        return lower;
    }

    //检查所有以sideLength为边长的正方形是否满足条件，有满足的则返回true，否则返回false
    private boolean check(int sideLength, int[][] prefixSum, int threshold){
        for (int i = sideLength; i < prefixSum.length; i++) {
            for (int j = sideLength; j < prefixSum[0].length; j++) {
                if (prefixSum[i][j] - prefixSum[i - sideLength][j] - prefixSum[i][j - sideLength]
                        + prefixSum[i - sideLength][j - sideLength] <= threshold){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] mat = {{1,1,3,2,4,3,2},{1,1,3,2,4,3,2},{1,1,3,2,4,3,2}};
        int threshold = 4;
        int result = new MaximumSideLengthOfASquareWithSumLessThanOrEqualToThreshold().maxSideLength(mat, threshold);
        System.out.println(result);
    }
}
