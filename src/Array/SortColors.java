package Array;

import java.util.Arrays;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 75
 * @date 2022/7/7 8:21
 */
public class SortColors {
    public void sortColors(int[] nums) {
        int temp;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[i] >= nums[j]){
                    temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        //int[] nums = {2,0,2,1,1,0};
        int[] nums = {2,0,1};
        new SortColors().sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
