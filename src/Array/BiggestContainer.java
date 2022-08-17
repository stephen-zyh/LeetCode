package Array;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 11
 * @date 2022/7/5 14:27
 */
public class BiggestContainer {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int h;
        int contain = 0;
        int new_contain;
        while (left <= right){
            h = Math.min(height[left], height[right]);
            new_contain = (right - left) * h;
            contain = Math.max(contain, new_contain);
            if (height[left] >= height[right]){
                right--;
            }else {
                left++;
            }
        }
        return contain;
    }

    public static void main(String[] args) {
        //int[] height = {1,8,6,2,5,4,8,3,7};
        int[] height = {1,1};
        System.out.println(new BiggestContainer().maxArea(height));
    }
}
