package Array;

import java.util.Arrays;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 57, 是SortColors的改进版本
 * @date 2022/7/7 8:54
 */
public class SortColors2 {
    public void sortColors(int[] nums) {
        int left = 0, right = nums.length - 1;
        int temp;
        int i = 0;
        while (i <= right){
            if (nums[i] == 0){
                //元素值为0，则向前交换，且继续向后遍历
                temp = nums[i];
                nums[i] = nums[left];
                nums[left] = temp;
                left++;
                i++;
            }else if (nums[i] == 2){
                //元素值为2，则向后交换，同时遍历的指针不移动，因为仍需判断交换后的元素的情况
                temp = nums[i];
                nums[i] = nums[right];
                nums[right] = temp;
                right--;
            }else {
                //不是前两种情况，该元素为1，移动指针
                i++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {2,0,2,1,1,0};
//        int[] nums = {2,0,1};
        new SortColors2().sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
