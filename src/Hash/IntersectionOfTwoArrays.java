package Hash;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 349
 * @date 2022/8/6 20:15
 */
public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> resultSet = new HashSet<>();
        //将nums1转化为集合
        for (int j : nums1) {
            set.add(j);
        }
        //一个元素在两个集合中都存在，则添加到结果集合中
        for (int j : nums2) {
            if (set.contains(j)) {
                resultSet.add(j);
            }
        }
        int[] result = new int[resultSet.size()];
        int i = 0;
        //集合转化为int数组返回
        for (Integer item : resultSet) {
            result[i] = item;
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {4,9,5}, nums2 = {9,4,9,8,4};
        int[] result = new IntersectionOfTwoArrays().intersection(nums1, nums2);
        System.out.println(Arrays.toString(result));
    }
}
