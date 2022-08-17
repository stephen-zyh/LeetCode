package Array;

import java.util.Arrays;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 48
 * @date 2022/7/6 10:56
 */
public class RotateImage {
    public void reverse(int[] nums, int start, int end){
        int temp = 0;
        for(int i = start, j = end; i < j; i++, j--){
            temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    public void rotate(int[][] matrix) {
        int temp = 0;
        //矩阵转置
        for (int i = 0; i < matrix.length; i++){
            for (int j = i + 1; j < matrix[i].length; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        //每一行reverse
        for (int i = 0; i < matrix.length; i++) {
            reverse(matrix[i], 0, matrix[i].length - 1);
        }
    }

    public static void main(String[] args) {
        //int[][] matrix = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        new RotateImage().rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
