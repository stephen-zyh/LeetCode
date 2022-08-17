package Hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 15
 * @date 2022/8/6 10:05
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        //如果数组长度小于3或者排序后第一个元素就大于0，则不可能有满足条件的结果
        if (nums.length < 3 || nums[0] > 0){
            return result;
        }

        for (int i = 0; i < nums.length; i++) {
            int first = nums[i];
            //跳过中间的重复项
            if (i > 0 && nums[i - 1] == nums[i]){
                continue;
            }
            HashSet<Integer> set = new HashSet<>();
            int tail = 0;
            for (int j = i + 1; j < nums.length; j++) {
                int third = nums[j];
                int second = -(first + third);
                //如果在set中能找到需要的second（之前遍历时没有在set找到配对而添加的项）
                if (set.contains(second)){
                    //如果first不同则顺序添加到最后
                    if (result.size() == 0 || result.get(result.size() - 1).get(0) != first){
                        tail = result.size();
                        result.add(new ArrayList<>(Arrays.asList(first, second, third)));
                    }else {
                        //first相同则按second降序排列
                        result.add(tail, new ArrayList<>(Arrays.asList(first, second, third)));
                    }
                    //跳过重复项
                    while (j < nums.length - 1 && nums[j] == nums[j + 1]){
                        j++;
                    }
                }
                //没有在set中找到对应，说明该元素不是作为third而是作为second出现
                set.add(third);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4,-2,-3,3,0,4};
        List<List<Integer>> result = new ThreeSum().threeSum(nums);
        System.out.println(Arrays.toString(result.toArray()));
    }
}
