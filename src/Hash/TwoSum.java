package Hash;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1
 * @date 2022/8/6 9:21
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //map中能找到对应则返回，否则添加到map中
            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            }
            map.put(target - nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
//        int[] nums = {2,7,11,15};
//        int target = 9;
        int[] nums = {3,2,4};
        int target = 6;
        int[] result = new TwoSum().twoSum(nums, target);
        System.out.println(Arrays.toString(result));
    }
}
