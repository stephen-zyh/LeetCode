package Array;

import java.util.Arrays;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 283
 * @date 2022/7/3 10:20
 */
public class MoveZero {
    public void moveZeroes(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0){
                if (slow != fast){
                    nums[slow] = nums[fast];
                    nums[fast] = 0;
                }
                slow++;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1};
        new MoveZero().moveZeroes(arr);
        System.out.println(Arrays.toString(arr));
    }
}
