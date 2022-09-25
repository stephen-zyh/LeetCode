package BinarySearch;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 81
 * @date 2022/9/18 9:16
 */
public class SearchInRotatedSortedArrayII {
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1, middle;
        while (left <= right){
            middle = left + (right - left) / 2; //不直接写成(left + right) / 2是为了防止溢出
            if (nums[middle] == target) return true;
            //去掉左侧重复项
            if (nums[left] == nums[middle]){
                left++;
                continue;
            }
            //当middle在翻转数组的右半部分时
            if (nums[middle] < nums[right]){
                //target的范围在nums[middle]和nums[right]之间，修改左边界，在[middle, right]中找
                //反之在[left,middle]中找
                if (target > nums[middle] && target <= nums[right]){
                    left = middle + 1;
                }else {
                    right = middle - 1;
                }
            }else {
                //与if分支同理
                if (target >= nums[left] && target < nums[middle]){
                    right = middle - 1;
                }else {
                    left = middle + 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        int[] nums = {2,5,6,0,0,1,2};
//        int target = 3;
//        int[] nums = {1,0,1,1,1};
//        int target = 0;
        int[] nums = {1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1};
        int target = 2;
        boolean result = new SearchInRotatedSortedArrayII().search(nums, target);
        System.out.println(result);
    }
}
