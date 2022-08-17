package Array;

import java.util.Arrays;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 167
 * @date 2022/7/4 8:57
 */
public class TwoSumII {
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target){
                    return new int[]{i + 1, j + 1};
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] numbers = {-1,0};
        int[] result = new TwoSumII().twoSum(numbers, 3);
        System.out.println(Arrays.toString(result));
    }
}
