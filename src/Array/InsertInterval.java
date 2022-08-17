package Array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 57
 * @date 2022/7/7 10:07
 */
public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        ArrayList<int[]> list = new ArrayList<>();
        int i = 0;
        //在newInterval左侧不相邻的区间不可能被合并，直接添加到list中
        while (i < intervals.length && intervals[i][1] < newInterval[0]){
            list.add(intervals[i]);
            i++;
        }
        int left = newInterval[0];
        if (i < intervals.length){
            //合并区间的下限取为相交区间的下限的最小值
            left = Math.min(intervals[i][0], newInterval[0]);
        }
        while (i < intervals.length && intervals[i][0] <= newInterval[1]){
            //跳过中间与newInterval相交的部分
            i++;
        }
        int right = newInterval[1];
        if (i > 0){
            //合并区间的上限取为相交区间的上限的最大值
            right = Math.max(intervals[i - 1][1], newInterval[1]);
        }
        list.add(new int[]{left, right});
        //在newInterval右侧不相邻的区间不可能被合并，直接添加到list中
        while (i < intervals.length){
            list.add(intervals[i]);
            i++;
        }

        int[][] result = new int[list.size()][2];
        for (int j = 0; j < list.size(); j++) {
            result[j] = list.get(j);
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] interval = {{1,2},{3,5},{6,7},{8,10},{12,16}};
//        int[][] interval = new int[0][2];
        int[] newInterval = {4,8};
        System.out.println(Arrays.deepToString(new InsertInterval().insert(interval, newInterval)));
    }
}
