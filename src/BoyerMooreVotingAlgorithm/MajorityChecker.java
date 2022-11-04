package BoyerMooreVotingAlgorithm;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1157，哪有什么摩尔投票，不过是暴力出奇迹罢了
 * @date 2022/11/4 9:32
 */
public class MajorityChecker {
    int[] arr;
    public MajorityChecker(int[] arr) {
        this.arr = arr;
    }

    public int query(int left, int right, int threshold) {
        int[] count = new int[20001];
        //统计arr在[left,right]之间各个元素出现的次数，如果有元素出现次数超过了阈值则返回，否则返回-1
        for (int i = left; i <= right; i++) {
            int num = arr[i];
            if (++count[num] == threshold)
                return num;
        }
        return -1;
    }

    public static void main(String[] args) {
        MajorityChecker majorityChecker = new MajorityChecker(new int[]{1, 1, 2, 2, 1, 1});
        System.out.println(majorityChecker.query(0, 5, 4));
        System.out.println(majorityChecker.query(0, 3, 3));
        System.out.println(majorityChecker.query(2, 3, 2));
    }
}
