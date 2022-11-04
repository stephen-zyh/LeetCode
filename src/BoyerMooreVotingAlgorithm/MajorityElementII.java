package BoyerMooreVotingAlgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 229，参考leetcode 169
 * @date 2022/11/3 20:07
 */
public class MajorityElementII {
    public List<Integer> majorityElement(int[] nums) {
        int candidate1 = -1, candidate2 = -2;
        int vote1 = 0, vote2 = 0;
        for (int num : nums){
            if (num == candidate1){
                vote1++;
            }else if (num == candidate2){
                vote2++;
            }else if (vote1 == 0){
                candidate1 = num;
                vote1++;
            }else if (vote2 == 0){
                candidate2 = num;
                vote2++;
            }else {
                vote1--;
                vote2--;
            }
        }
        //最后留下来的两个候选人未必就是结果，如[1,2,3,1,2,3,4,5]，故需要对结果进行验证
        int count1 = 0, count2 = 0;
        for (int num : nums){
            if (num == candidate1)  count1++;
            if (num == candidate2)  count2++;
        }
        List<Integer> result = new ArrayList<>();
        if (count1 > nums.length / 3)   result.add(candidate1);
        if (count2 > nums.length / 3)   result.add(candidate2);
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,3};
        List<Integer> result = new MajorityElementII().majorityElement(nums);
        System.out.println(result);
    }
}
