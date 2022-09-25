package BinarySearch;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 74
 * @date 2022/9/18 18:18
 */
public class SearchA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int left = 0, right = row * col - 1, middle;
        //将二维数组转化为一维升序数组进行处理
        while (left <= right){
            middle = left + (right - left) / 2;
            int middleRow = middle / col;
            int middleCol = middle % col;
            if (matrix[middleRow][middleCol] < target){
                left = middle + 1;
            }else if (matrix[middleRow][middleCol] == target){
                return true;
            }else {
                right = middle - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int target = 3;
        boolean result = new SearchA2DMatrix().searchMatrix(matrix, target);
        System.out.println(result);
    }
}
