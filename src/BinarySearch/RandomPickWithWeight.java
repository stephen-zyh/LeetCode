package BinarySearch;

import java.util.Random;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 528
 * @date 2022/9/23 16:08
 */
public class RandomPickWithWeight {
    int[] prefixSum;
    Random random;
    int sum;
    public RandomPickWithWeight(int[] w) {
        random = new Random();
        prefixSum = new int[w.length];
        //求前缀和
        for (int i = 0; i < w.length; i++) {
           sum += w[i];
           prefixSum[i] = sum;
        }
    }

    public int pickIndex() {
        int target = random.nextInt(sum) + 1;
        int left = 0, right = prefixSum.length - 1;
        //确定target所在区间，区间长度对应着相应数字出现的概率
        while (left < right){
            int middle = left + (right - left) / 2;
            if (prefixSum[middle] < target){
                left = middle + 1;
            }else {
                right = middle;
            }
        }
        return left;
    }
}
