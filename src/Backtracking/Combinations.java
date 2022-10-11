package Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 77
 * @date 2022/10/8 11:37
 */
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        calculateCombination(n, k, 1, new ArrayList<>(), result);
        return result;
    }

    private void calculateCombination(int n, int k, int start, List<Integer> current, List<List<Integer>> result){
        //当前结果已经是k个数的组合，将其添加到result中
        if (k == current.size()){
            result.add(new ArrayList<>(current));
            return;
        }
        //为避免元素和结果的重复，从current中最后一个元素索引的下一个开始遍历
        for (int i = start; i <= n; i++) {
            //回溯
            current.add(i);
            calculateCombination(n, k, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        int n = 4, k = 2;
        List<List<Integer>> result = new Combinations().combine(n, k);
        System.out.println(result);
    }
}
