package BinarySearch;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 153
 * @date 2022/9/18 9:54
 */
public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right){
            int middle = left + (right - left) / 2;
            //分为翻转一侧和未翻转一侧进行讨论
            if (nums[middle] < nums[right]){
                right = middle;
            }else {
                left = middle + 1;
            }
        }
        return nums[right];
    }

    public static void main(String[] args) {
        int[] nums = {3,4,5,1,2};
        int result = new FindMinimumInRotatedSortedArray().findMin(nums);
        System.out.println(result);
    }
}
