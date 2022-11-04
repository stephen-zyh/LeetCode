package BoyerMooreVotingAlgorithm;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 169
 * @date 2022/11/3 19:27
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
        int vote = 0, candidate = -1;
        for (int i = 0; i < nums.length; i++) {
            //vote为0时更新候选人，如果当前的票是投给当前候选人的，候选人得票数加1，反之若投给对手，当前候选人优势变小，相当于抵消一票
            if (vote == 0){
                candidate = nums[i];
            }
            if (nums[i] != candidate){
                vote--;
            }else {
                vote++;
            }
        }
        return candidate;
    }

    public static void main(String[] args) {
        int[] nums = {2,2,1,1,1,2,2};
        int result = new MajorityElement().majorityElement(nums);
        System.out.println(result);
    }
}
