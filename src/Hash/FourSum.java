package Hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 18
 * @date 2022/8/6 16:28
 */
public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        //不足四项时没有合乎要求的结果
        if (nums.length < 4){
            return result;
        }
        for (int i = 0; i < nums.length - 3; i++) {
            //跳过中间的重复项
            if (i > 0 && nums[i - 1] == nums[i]){
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                //跳过中间重复项
                if (j > i + 1 && nums[j - 1] == nums[j]){
                    continue;
                }
                //双指针遍历后两个元素
                int left = j + 1;
                int right = nums.length - 1;
                //转成long是为了通过nums = {1000000000,1000000000,1000000000,1000000000}，target = -294967296这个无聊的用例
                long temp = (long) nums[i] + nums[j] + nums[left] + nums[right];
                while (left < right){
                    if (temp > target){
                        right--;
                    }else if (temp < target){
                        left++;
                    }else {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        //跳过中间的重复项
                        while (left < right && nums[left] == nums[left + 1]){
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]){
                            right--;
                        }
                        left++;
                        right--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        int[] nums = {1,0,-1,0,-2,2};
//        int target = 0;
//        int[] nums = {2,2,2,2};
//        int target = 8;
        int[] nums = {1000000000,1000000000,1000000000,1000000000};
        int target = -294967296;
        List<List<Integer>> result = new FourSum().fourSum(nums, target);
        System.out.println(Arrays.toString(result.toArray()));
    }
}
