package BinarySearch;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 4
 * @date 2022/9/21 9:38
 */
public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int totalLen = len1 + len2;
        //总长度是奇数则找到中间的数即可，总长度是偶数则是中间两个数的平均数
        if ((totalLen & 1) != 0){
            return getKthElement(nums1, nums2, totalLen / 2 + 1);
        }else {
            return (getKthElement(nums1, nums2, totalLen / 2) + getKthElement(nums1, nums2, totalLen / 2 + 1)) / 2.0;
        }
    }

    //找到两个数组中第k大的数
    private int getKthElement(int[] nums1, int[] nums2, int k){
        int len1 = nums1.length;
        int len2 = nums2.length;
        int index1 = 0, index2 = 0;

        while (true){
            //一个数组已经遍历结束或者长度为0，直接返回另一个数组中对应位置的元素
            if (index1 == len1){
                return nums2[index2 + k - 1];
            }
            if (index2 == len2){
                return nums1[index1 + k - 1];
            }
            //如果 k == 1，我们只要返回两个数组首元素的最小值即可。
            if (k == 1){
                return Math.min(nums1[index1], nums2[index2]);
            }
            int half = k / 2;
            //如果A[k/2-1]或者B[k/2−1]越界，那么我们可以选取对应数组中的最后一个元素。
            // 在这种情况下，我们必须根据排除数的个数减少 k 的值，而不能直接将 k 减去 k/2。
            int newIndex1 = Math.min(index1 + half, len1) - 1;
            int newIndex2 = Math.min(index2 + half, len2) - 1;
            if (nums1[newIndex1] <= nums2[newIndex2]){
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            }else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2}, nums2 = {3,4};
        double result = new MedianOfTwoSortedArrays().findMedianSortedArrays(nums1, nums2);
        System.out.println(result);
    }
}
