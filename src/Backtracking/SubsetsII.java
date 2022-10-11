package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 90
 * @date 2022/10/7 16:46
 */
public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        Arrays.sort(nums);
        generateSubsets(nums, 0, result, new ArrayList<>());
        return result;
    }

    private void generateSubsets(int[] nums, int startIndex, List<List<Integer>> subsets, List<Integer> subset){
        if (startIndex < nums.length){
            for (int i = startIndex; i < nums.length; i++) {
                //去重
                if (i > startIndex && nums[i] == nums[i - 1]){
                    continue;
                }
                //回溯
                subset.add(nums[i]);
                subsets.add(new ArrayList<>(subset));
                generateSubsets(nums, i + 1, subsets, subset);
                subset.remove(subset.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,2};
        List<List<Integer>> list = new SubsetsII().subsetsWithDup(nums);
        System.out.println(list);
    }
}
