package Array;

import java.util.Arrays;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 88
 * @date 2022/7/2 10:33
 */
public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //将nums2赋值给nums1为0部分,实现数组合并
        int len1 = nums1.length, len2 = nums2.length;
        for (int i = 0; i < len2; i++) {
            nums1[len1 - len2 + i] = nums2[i];
        }
        //对合并后的数组排序
        int temp = 0;
        for (int i = 0; i < len1; i++) {
            for (int j = i; j < len1; j++) {
                if (nums1[i] > nums1[j]){
                    temp = nums1[i];
                    nums1[i] = nums1[j];
                    nums1[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
        new MergeSortedArray().merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }
}
