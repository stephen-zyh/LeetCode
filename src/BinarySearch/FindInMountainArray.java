package BinarySearch;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1095
 * @date 2022/9/15 9:32
 */
public class FindInMountainArray {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int peekElementIndex = getPeekElementIndex(mountainArr);
        int peekValue = mountainArr.get(peekElementIndex);
        //target与峰值比较，等于则直接返回索引；大于则找不到，返回-1。
        if (peekValue == target){
            return peekElementIndex;
        }
        if (peekValue < target){
            return -1;
        }
        //现在峰值左侧找，找不到再去右侧找
        int index = findInAscendingPart(mountainArr, 0, peekElementIndex - 1, target);
        if (index != -1){
            return index;
        }
        return findInDescendingPart(mountainArr, peekElementIndex + 1, mountainArr.length() - 1, target);
    }

    //寻找峰值元素的索引
    private int getPeekElementIndex(MountainArray mountainArray){
        int len = mountainArray.length();
        int left = 1, right = len - 2;
        while (left < right){
            int middle = (left + right) / 2;
            if (mountainArray.get(middle) < mountainArray.get(middle + 1)){
                left = middle + 1;
            }else {
                right = middle;
            }
        }
        return left;
    }

    //二分法在峰值左侧寻找目标值
    private int findInAscendingPart(MountainArray mountainArray, int low, int high, int target){
        while (low <= high){
            int middle = (low + high) / 2;
            int curr = mountainArray.get(middle);
            if (curr == target){
                return middle;
            }else if (curr > target){
                high = middle - 1;
            }else {
                low = middle + 1;
            }
        }
        return -1;
    }

    //二分法在峰值右侧寻找目标值
    private int findInDescendingPart(MountainArray mountainArray, int low, int high, int target){
        while (low <= high){
            int middle = (low + high) / 2;
            int curr = mountainArray.get(middle);
            if (curr == target){
                return middle;
            }else if (curr < target){
                high = middle - 1;
            }else {
                low = middle + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        MountainArray mountainArray = new WrappedArray(new int[]{1,2,3,4,5,3,1});
        int index = new FindInMountainArray().findInMountainArray(3, mountainArray);
        System.out.println(index);
    }
}

//MountainArray的一个实现类
class WrappedArray implements MountainArray{
    int[] arr;
    public WrappedArray(int[] array){
        arr = array;
    }
    @Override
    public int get(int index) {
        return arr[index];
    }

    @Override
    public int length() {
        return arr.length;
    }
}
