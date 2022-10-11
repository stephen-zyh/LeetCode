package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 40，本题是leetcode 39改进而来
 * @date 2022/10/8 11:06
 */
public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);    //先将数组排序，方便在子序列和一旦大于target时就可以break，节省时间
        List<List<Integer>> result = new ArrayList<>();
        calculateSum(candidates, target, 0, 0, new ArrayList<>(), result);
        return result;
    }

    private void calculateSum(int[] candidates, int target, int currentSum, int startIndex, List<Integer> sum, List<List<Integer>> result){
        //列表元素和等于target，找到一个答案，添加到结果中
        if (currentSum == target){
            result.add(new ArrayList<>(sum));
        }
        for (int i = startIndex; i < candidates.length; i++) {
            //去重，相同的元素在一次迭代过程中只能添加一次
            if (i > startIndex && candidates[i] == candidates[i - 1]){
                continue;
            }
            if (currentSum + candidates[i] <= target){
                //回溯操作
                sum.add(candidates[i]);
                calculateSum(candidates, target, currentSum + candidates[i], i + 1, sum, result);
                sum.remove(sum.size() - 1);
            }else {
                //数组是有序的，一旦currentSum + candidates[i] > target就没有继续遍历下去的必要了
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] candidates = {10,1,2,7,6,1,5};
        int target = 8;
        List<List<Integer>> result = new CombinationSumII().combinationSum2(candidates, target);
        System.out.println(result);
    }
}
