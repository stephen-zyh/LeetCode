package Array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 442
 * @date 2022/7/6 10:02
 */
public class FindAllDuplicates {
    public List<Integer> findDuplicates(int[] nums) {
        int len = nums.length;
        //occu_time用来记录区间[1, n]中每个数出现的次数
        int[] occu_times = new int[len];
        for (int i = 0; i < len; i++) {
            //数字n出现的次数存在occu_times[n - 1]中
            occu_times[nums[i] - 1] += 1;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (occu_times[i] == 2){
                list.add(i + 1);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        //int[] nums = {4,3,2,7,8,2,3,1};
        int[] nums = {1,1,2};
        System.out.println(new FindAllDuplicates().findDuplicates(nums).toString());
    }
}
