package Backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 491
 * @date 2022/10/8 16:24
 */
public class IncreasingSubsequences {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        addResult(nums, -1, new ArrayList<>(), result);
        return result;
    }

    private void addResult(int[] nums, int index, List<Integer> current, List<List<Integer>> result){
        //只要序列长度大于1且递增即可加入到结果中
        if (current.size() > 1){
            result.add(new ArrayList<>(current));
        }
        Set<Integer> set = new HashSet<>();
        for (int i = index + 1; i < nums.length; i++) {
            //同一个数字可能重复出现，但在每一轮迭代中只添加一次
            if (set.contains(nums[i])){
                continue;
            }
            set.add(nums[i]);
            //index==-1是加第一个数字时的情况，nums[i] >= nums[index]是递增序列的条件判断
            if (index == -1 || nums[i] >= nums[index]){
                //回溯
                current.add(nums[i]);
                addResult(nums, i, current, result);
                current.remove(current.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {4,6,7,7};
        List<List<Integer>> result = new IncreasingSubsequences().findSubsequences(nums);
        System.out.println(result);
    }
}
