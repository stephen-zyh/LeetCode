package Hash;

import java.util.HashMap;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 974
 * @date 2022/8/8 9:35
 */
public class SubarraySumsDivisibleByK {
    public int subarraysDivByK(int[] nums, int k) {
        int result = 0;
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            sum += num;
            int reminder = sum % k;
            //-n和k-n在模k下是同余的，二者等价，因此要将负的reminder转化成正的
            if (reminder < 0) {
                reminder += k;
            }
            if (map.containsKey(reminder)) {
                if (reminder != 0){
                    //两个reminder不为零且相等的数相减能被k整除
                    result += map.get(reminder);
                }else {
                    //reminder为零的数本身能被k整除，两个reminder为零的数之差也能被k整除
                    result += map.get(reminder) + 1;
                }
            }
            map.put(reminder, map.getOrDefault(reminder, 0) + 1);
        }
        //在第一次添加reminder=0的元素时没有算次数，这里补上
        if (map.containsKey(0)){
            result += 1;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4,5,0,-2,-3,1};
        int k = 5;
//        int[] nums = {-1, 2, 9};
//        int k = 2;
        int result = new SubarraySumsDivisibleByK().subarraysDivByK(nums, k);
        System.out.println(result);
    }
}
