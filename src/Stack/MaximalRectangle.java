package Stack;

import java.util.Stack;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 85，参考leetcode 84
 * @date 2022/8/5 10:57
 */
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int maxArea = 0;
        int col = matrix.length;
        int row = matrix[0].length;
        int[] heights = new int[row];
        //参考84题，将前i行视作一个直方图，求其中的最大面积
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                //该元素为0，说明该列直方图高度为0，否则其高度加1
                if (matrix[i][j] == '1'){
                    heights[j]++;
                }else {
                    heights[j] = 0;
                }
            }
            //每遍历一行求一次最大值
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        return maxArea;
    }

    //leetcode 84求最大面积的方法
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int curIndex = 0, maxArea = 0;
        while (curIndex < heights.length){
            while (stack.peek() != -1 && heights[curIndex] < heights[stack.peek()]){
                maxArea = Math.max(maxArea, heights[stack.pop()] * (curIndex - stack.peek() - 1));
            }
            stack.push(curIndex++);
        }
        while (stack.peek() != -1){
            maxArea = Math.max(maxArea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        }
        return maxArea;
    }

    public static void main(String[] args) {
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        int result = new MaximalRectangle().maximalRectangle(matrix);
        System.out.println(result);
    }
}
