package Stack;

import java.util.Stack;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 84
 * @date 2022/8/2 11:37
 */
public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        //入栈的元素都为正，压入-1，当栈顶为-1表示栈空
        stack.push(-1);
        int curIndex = 0, maxArea = 0;
        //维护单调递增栈，当当前元素比栈顶小时，再依次计算栈中元素所能构成的最大面积
        while (curIndex < heights.length){
            while (stack.peek() != -1 && heights[curIndex] < heights[stack.peek()]){
                maxArea = Math.max(maxArea, heights[stack.pop()] * (curIndex - stack.peek() - 1));
            }
            stack.push(curIndex++);
        }
        //最后可能还有递增的元素在栈中，需要计算剩余元素所能构成的矩形的最大面积
        while (stack.peek() != -1){
            maxArea = Math.max(maxArea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        }
        return maxArea;
    }

    public static void main(String[] args) {
//        int[] heights = {2,1,5,6,2,3};
        int[] heights = {2,4};
        int maxArea = new LargestRectangleInHistogram().largestRectangleArea(heights);
        System.out.println(maxArea);
    }
}
