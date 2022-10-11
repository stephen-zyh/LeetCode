package Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 78
 * @date 2022/10/7 15:19
 */
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        generateSubsets(nums, 0, result, new ArrayList<>());
        return result;
    }

    private void generateSubsets(int[] nums, int startIndex, List<List<Integer>> subsets, List<Integer> subset){
        if (startIndex < nums.length){
            for (int i = startIndex; i < nums.length; i++) {
                //回溯
                subset.add(nums[i]);
                subsets.add(new ArrayList<>(subset));
                generateSubsets(nums, i + 1, subsets, subset);
                subset.remove(subset.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> subsets = new Subsets().subsets(nums);
        System.out.println(subsets);
    }
}
