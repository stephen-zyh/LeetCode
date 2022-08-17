package Stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 496
 * @date 2022/7/27 7:54
 */
public class NextGreaterElementI {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            //当当前元素小于栈顶元素时，继续将它入栈
            //当当前元素大于栈顶元素时，栈顶元素出栈，此时应将该出栈的元素与当前元素形成key-value键值对，存入HashMap中
            while (!stack.isEmpty() && stack.peek() < nums2[i]){
                map.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }
        //遍历nums1，如果在hashMap中有映射则返回映射，当找不到时则为-1
        //题目未要求不可修改nums1[]，其长度与结果数组一致，可以直接使用，减少空间开销
        for (int i = 0; i < nums1.length; i++) {
            if (map.get(nums1[i]) != null){
                nums1[i] = map.get(nums1[i]);
            }else {
                nums1[i] = -1;
            }
        }
        return nums1;
    }

    public static void main(String[] args) {
        int[] nums1 = {4,1,2}, nums2 = {1,3,4,2};
//        int[] nums1 = {2,4}, nums2 = {1,2,3,4};
        int[] result = new NextGreaterElementI().nextGreaterElement(nums1, nums2);
        System.out.println(Arrays.toString(result));
    }
}
