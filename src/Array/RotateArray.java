package Array;

import java.util.Arrays;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 189
 * @date 2022/7/1 21:12
 */
public class RotateArray {
    public void reverse(int[] nums, int start, int end){
        int temp = 0;
        for(int i = start, j = end; i < j; i++, j--){
            temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        reverse(nums, 0, len - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, len - 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        RotateArray rotateArray = new RotateArray();
        rotateArray.rotate(arr, 3);
        System.out.println(Arrays.toString(arr));
    }
}
