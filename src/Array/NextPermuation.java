package Array;

import java.util.Arrays;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 31
 * @date 2022/7/6 8:13
 */
public class NextPermuation {
    public void reverse(int[] nums, int start, int end){
        int temp = 0;
        for(int i = start, j = end; i < j; i++, j--){
            temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    public void nextPermutation(int[] nums) {
        int len = nums.length;
        int flag = 0;
        int start = 0;
//        for (int i = len - 1; i > 0; i--) {
//            for (int j = i - 1; j >= 0; j--) {
//                if (nums[i] > nums[j]){
//                    int temp = nums[i];
//                    nums[i] = nums[j];
//                    nums[j] = temp;
//                    start = j + 1;
////                    flag = 1;
////                    break;
//                }
//            }
////            if (flag == 1){
////                break;
////            }
//        }
        for (int i = len - 1; i >= 0; i--) {
            for (int j = len - 1; j > i; j--) {
                if (nums[j] > nums[i]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    start = i + 1;
                    flag = 1;
                    break;
                }
            }
            if (flag == 1){
                break;
            }
        }
        reverse(nums, start, len - 1);
    }

    public static void main(String[] args) {
        int[] nums = {4,2,0,2,3,2,0};
        new NextPermuation().nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
