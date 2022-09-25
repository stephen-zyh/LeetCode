package BinarySearch;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 154，参考leetcode 153
 * @date 2022/9/18 16:59
 */
public class FindMinimumInRotatedSortedArrayII {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right){
            int middle = left + (right - left) / 2;
            if (nums[middle] < nums[right]){
                right = middle;
            }else if (nums[middle] > nums[right]){
                left = middle + 1;
            }else {
                right--;    //跳过重复元素
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5};
        int result = new FindMinimumInRotatedSortedArrayII().findMin(nums);
        System.out.println(result);
    }
}
