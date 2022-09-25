package BinarySearch;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 162，在leetcode 852基础上稍作改进
 * @date 2022/9/14 20:15
 */
public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        //因为将数组的越界部分视作无穷小，当数组长度为1时返回这个元素索引，长度为2时返回大者的索引
        if(nums.length == 1){
            return 0;
        }
        if(nums.length == 2){
            return nums[0] > nums[1] ? 0 : 1;
        }
        int left = 1, right = nums.length - 2, middle;  //left和right可以分别初始化为1和arr.length - 2，避免while中出现数组越界
        //二分法，在数组中间找一个符合条件的峰值的索引
        while (left <= right){
            middle = (left + right) / 2;
            if (nums[middle] < nums[middle + 1]){
                left = middle + 1;
            }else if (nums[middle] > nums[middle - 1] && nums[middle] > nums[middle + 1]){
                return middle;
            }else {
                right = middle - 1;
            }
        }
        //如果在去除首尾元素的数组中没有找到峰值的索引，说明峰值出现在首尾，返回首尾之中更大的元素的索引
        return nums[0] > nums[nums.length - 1] ? 0 : nums.length - 1;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,1,3,5,6,4};
        int result = new FindPeakElement().findPeakElement(nums);
        System.out.println(result);
    }
}
