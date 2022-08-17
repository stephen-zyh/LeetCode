package Array;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 941
 * @date 2022/7/3 9:06
 */
public class MountainArray {
    public boolean validMountainArray(int[] arr) {
        int top = 0;
        //找潜在的山峰
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]){
                top = i;
            }else{
                break;
            }
        }
        //山峰一定要在数组的中间位置
        if (top == 0 || top == arr.length - 1){
            return false;
        }
        //山峰要比后续元素大，并且后续元素按递减排列
        for (int j = top + 1; j < arr.length; j++) {
            if (arr[top] > arr[j] && arr[j] < arr[j - 1]){
                continue;
            }else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr = {1,7,9,5,4,1,2};
        System.out.println(new MountainArray().validMountainArray(arr));
    }
}