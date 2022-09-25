package BinarySearch;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 33
 * @date 2022/9/18 8:29
 */
public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        if (nums.length == 0){
            return -1;
        }
        int left = 0, right = nums.length - 1, middle;
        while (left <= right){
            middle = left + (right - left) / 2;
            //恰好遇到目标值，直接返回下标
            if (nums[middle] == target){
                return middle;
            }
            //当middle在翻转数组的右半部分时
            if (nums[middle] < nums[right]){
                //target的范围在nums[middle]和nums[right]之间，修改左边界，在[middle, right]中找
                //反之在[left,middle]中找
                if (target > nums[middle] && target < nums[right]){
                    left = middle + 1;
                }else {
                    right = middle - 1;
                }
            }else {
                //与if分支同理
                if (target > nums[left] && target < nums[middle]){
                    right = middle - 1;
                }else {
                    left = middle + 1;
                }
            }
        }
        //未找到target的情况
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        int result = new SearchInRotatedSortedArray().search(nums, target);
        System.out.println(result);
    }
}
