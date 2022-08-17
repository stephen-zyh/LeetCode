package Array;

import java.util.Arrays;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 88 改进版，时间复杂度为O(m+n)
 * @date 2022/7/2 11:28
 */
public class MergeSortedArray2 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int pointer = m + n - 1;
        m--;
        n--;
        while (n >= 0){
            if (m >= 0 && nums1[m] > nums2[n]){
                nums1[pointer] = nums1[m];
                m--;
            }else {
                nums1[pointer] = nums2[n];
                n--;
            }
            pointer--;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {0,0,0,0,0};
        int[] nums2 = {1,2,3,4,5};
        new MergeSortedArray().merge(nums1, 0, nums2, 5);
        System.out.println(Arrays.toString(nums1));
    }
}
