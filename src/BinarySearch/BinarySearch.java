package BinarySearch;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 704
 * @date 2022/9/15 10:38
 */
public class BinarySearch {
    public int search(int[] nums, int target) {
        //由于数组升序，遇到下述情况直接返回-1
        if (target > nums[nums.length - 1] || target < nums[0]){
            return -1;
        }
        int left = 0, right = nums.length - 1;
        //二分查找
        while (left <= right){
            int middle = (left + right) / 2;
            if (nums[middle] == target){
                return middle;
            }else if (nums[middle] < target){
                left = middle + 1;
            }else {
                right = middle - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,3,5,9,12};
        int target = 9;
        int result = new BinarySearch().search(nums, target);
        System.out.println(result);
    }
}
