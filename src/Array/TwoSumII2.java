package Array;

import java.util.Arrays;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 167, 改进版，使用双指针遍历一次，降低时间复杂度
 * @date 2022/7/4 9:10
 */
public class TwoSumII2 {
    public int[] twoSum(int[] numbers, int target){
        int left = 0, right = numbers.length - 1;
        while (numbers[left] + numbers[right] != target){
            if (numbers[left] + numbers[right] < target){
                left++;
            }else{
                right--;
            }
        }
        return new int[]{left + 1, right + 1};
    }
    public static void main(String[] args) {
        int[] numbers = {-1,0};
        int[] result = new TwoSumII2().twoSum(numbers, -1);
        System.out.println(Arrays.toString(result));
    }
}
