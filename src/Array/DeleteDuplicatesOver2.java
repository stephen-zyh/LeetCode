package Array;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 80, 是leetcode 26的进阶版本，代码在其基础上修改而来
 * @date 2022/7/5 6:42
 */
public class DeleteDuplicatesOver2 {
    public int removeDuplicates(int[] nums) {
        /**
         * 新增计数变量count，用于记录某个元素是否重复出现两次以上
         * count的初值设为1，因为从首个元素开始遍历，该元素已经出现一次
         */
        int slow = 0, count = 1;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]){
                count = 0;
                slow++;
                nums[slow] = nums[fast];
                count++;
            }else if (nums[fast] == nums[slow] && fast != slow && count < 2){
                //fast != slow条件是为避免数组只有一个元素时出现数组脚标越界的错误
                slow++;
                nums[slow] = nums[fast];
                count++;
            }
        }
        return slow + 1;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,0,0,1,1,2,3,3,3,5,5,5,5,7,7,9,9,9,10,10,11,11};
        int k = new DeleteDuplicatesOver2().removeDuplicates(nums);
        System.out.println(k);
        for (int i = 0; i < k; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
