package Hash;

import java.util.HashMap;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 560
 * @date 2022/8/7 17:13
 */
public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int result = 0;
        int sum = 0;    //sum是前缀和
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            sum += num;
            //求得sum，若map中已有sum-k，则必然有中间一段和为k
            if (map.containsKey(sum - k)) {
                result += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1};
        int k = 2;
        int result = new SubarraySumEqualsK().subarraySum(nums, k);
        System.out.println(result);
    }
}
