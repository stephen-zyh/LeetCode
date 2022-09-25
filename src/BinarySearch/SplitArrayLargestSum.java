package BinarySearch;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 410
 * @date 2022/9/20 12:00
 */
public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int m) {
        int minSum = 0, maxSum = 0;
        //确定上下界，下界是数组中最大的元素（保证每个元素都至少能被分配到一个块中），上界是数组所有元素的和（至少要分成一组）
        for (int num : nums){
            maxSum += num;
            minSum = Math.max(num, minSum);
        }
        //二分，由于需要求得最小的最大值，每次求得可行方案时都修改上界
        while (minSum < maxSum){
            int middle = minSum + (maxSum - minSum) / 2;
            if (possibleScheme(middle, nums) > m){
                minSum = middle + 1;
            }else {
                maxSum = middle;
            }
        }
        return minSum;
    }
    //给定容量（即每一部分的最大值），统计能够分割为多少块
    private int possibleScheme(int capacity, int[] nums){
        int load = 0, parts = 0;
        for (int num : nums){
            if (load + num <= capacity){
                load += num;
            }else {
                parts++;
                load = num;
            }
        }
        if (load > 0){
            parts++;
        }
        return parts;
    }

    public static void main(String[] args) {
//        int[] nums = {7,2,5,10,8};
//        int m = 2;
//        int[] nums = {1,2,3,4,5};
//        int m = 2;
        int[] nums = {1,4,4};
        int m = 3;
        int result = new SplitArrayLargestSum().splitArray(nums, m);
        System.out.println(result);
    }
}
