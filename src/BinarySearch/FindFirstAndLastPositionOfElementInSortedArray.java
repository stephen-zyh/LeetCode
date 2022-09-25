package BinarySearch;

import java.util.Arrays;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 34
 * @date 2022/9/15 20:39
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        //数组长度为0时直接返回[-1,-1]
        if (nums.length == 0){
            return new int[]{-1, -1};
        }
        return new int[]{findFirst(nums, target), findLast(nums, target)};
    }
    private int findFirst(int[] nums, int target){
        int left = 0, right = nums.length - 1, middle;
        while (left <= right){
            middle = (left + right) / 2;
            if (nums[middle] == target){
                //找到的元素是最左侧元素或者target第一次出现的位置，返回索引，否则说明第一次出现的位置在middle左侧
                if (middle == 0 || target > nums[middle - 1]){
                    return middle;
                }else {
                    right = middle - 1;
                }
            }else if (nums[middle] > target){
                right = middle - 1;
            }else {
                left = middle + 1;
            }
        }
        return -1;
    }
    private int findLast(int[] nums, int target){
        int left = 0, right = nums.length - 1, middle;
        while (left <= right){
            middle = (left + right) / 2;
            if (nums[middle] == target){
                //与findFirst同理
                if (middle == nums.length - 1 || target < nums[middle + 1]){
                    return middle;
                }else {
                    left = middle + 1;
                }
            }else if (nums[middle] > target){
                right = middle - 1;
            }else {
                left = middle + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        int target = 8;
        int[] result = new FindFirstAndLastPositionOfElementInSortedArray().searchRange(nums, target);
        System.out.println(Arrays.toString(result));
    }
}
