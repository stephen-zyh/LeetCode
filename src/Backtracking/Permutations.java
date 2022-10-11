package Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 46
 * @date 2022/10/8 11:17
 */
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        boolean[] used = new boolean[nums.length];  //一个元素在排列中使用过了便将其设为true，不再继续使用
        List<List<Integer>> result = new ArrayList<>();
        calculatePermutation(nums, used, new ArrayList<>(), result);
        return result;
    }

    private void calculatePermutation(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result){
        //找到一个组合，将其添加到结果中并返回
        if (current.size() == nums.length){
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //回溯，只能添加之前没有添加过的（used[i]为false的）
            if (!used[i]){
                used[i] = true;
                current.add(nums[i]);
                calculatePermutation(nums, used, current, result);
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> result = new Permutations().permute(nums);
        System.out.println(result);
    }
}
