package Array;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 42
 * @date 2022/7/7 14:54
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int maxLeft = height[left], maxRight = height[right];
        int totalWater = 0;
        while (left < right){
            if (maxLeft <= maxRight){
                left++;
                totalWater += Math.max(0, maxLeft - height[left]);
                maxLeft = Math.max(maxLeft, height[left]);
            }else {
                right--;
                totalWater += Math.max(0, maxRight - height[right]);
                maxRight = Math.max(maxRight, height[right]);
            }
        }
        return totalWater;
    }

    public static void main(String[] args) {
//        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] height = {4,2,0,3,2,5};
        System.out.println(new TrappingRainWater().trap(height));
    }
}
