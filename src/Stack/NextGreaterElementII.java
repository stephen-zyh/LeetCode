package Stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 503
 * @date 2022/7/28 7:32
 */
public class NextGreaterElementII {
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int len = nums.length;
        int[] result = new int[len];
        Arrays.fill(result, -1);    //如果当前值最邻近的最大值未找到则默认为-1
        //与leetcode 496类似，但依据题意需遍历数组两次
        for (int i = 0; i < len * 2; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % len]){
                result[stack.pop()] = nums[i % len];
            }
            stack.push(i % len);
        }
        return result;
    }

    public static void main(String[] args) {
        int[]  nums = {1,2,1};
//        int[] nums = {100,1,11,1,120,111,123,1,-1,-100};
        int[] result = new NextGreaterElementII().nextGreaterElements(nums);
        System.out.println(Arrays.toString(result));
    }
}
