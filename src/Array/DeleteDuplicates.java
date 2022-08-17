package Array;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 26
 * @date 2022/7/4 9:40
 */
public class DeleteDuplicates {
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] == nums[slow]){
                continue;
            }else{
                slow++;
                nums[slow] = nums[fast];
            }
        }
        return slow + 1;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,0,0,1,1,2,3,3,3,5,5,5,5,7,7,9,9,9,10,10,11,11};
        int k = new DeleteDuplicates().removeDuplicates(nums);
        System.out.println(k);
        for (int i = 0; i < k; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
