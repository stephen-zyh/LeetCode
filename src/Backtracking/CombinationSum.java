package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 39
 * @date 2022/10/8 10:41
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);    //先将数组排序，方便在子序列和一旦大于target时就可以break，节省时间
        List<List<Integer>> result = new ArrayList<>();
        calculateSum(candidates, target, 0, 0, new ArrayList<>(), result);
        return result;
    }

    private void calculateSum(int[] candidates, int target, int currentSum, int startIndex, List<Integer> sum, List<List<Integer>> result){
        if (currentSum == target){
            result.add(new ArrayList<>(sum));
        }
        //允许同一元素重复使用，因此从startIndex开始往后遍历
        for (int i = startIndex; i < candidates.length; i++) {
            if (currentSum + candidates[i] <= target){
                //回溯操作
                sum.add(candidates[i]);
                calculateSum(candidates, target, currentSum + candidates[i], i, sum, result);
                sum.remove(sum.size() - 1);
            }else {
                //数组是有序的，一旦currentSum + candidates[i] > target就没有继续遍历下去的必要了
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        int target = 7;
        List<List<Integer>> result = new CombinationSum().combinationSum(candidates, target);
        System.out.println(result);
    }
}
