package BinarySearch;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 852
 * @date 2022/9/14 17:55
 */
public class PeakIndexInAMountainArray {
    public int peakIndexInMountainArray(int[] arr) {
        //数组长度不小于3，峰值一定是在中间取得，因此left和right可以分别初始化为1和arr.length - 2，避免while中出现数组越界
        int left = 1, right = arr.length - 2, middle;
        //二分法求峰值
        while (left <= right){
            middle = (left + right) / 2;
            if (arr[middle] < arr[middle + 1]){
                left = middle + 1;
            }else if (arr[middle] > arr[middle - 1] && arr[middle] > arr[middle + 1]){
                return middle;
            }else {
                right = middle - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {24,69,100,99,79,78,67,36,26,19};
        int result = new PeakIndexInAMountainArray().peakIndexInMountainArray(arr);
        System.out.println(result);
    }
}
