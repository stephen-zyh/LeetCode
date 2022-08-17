package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 56
 * @date 2022/7/5 8:42
 */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        int rowlen = intervals.length;
        int[] flag = new int[rowlen];
        //区间A：下限为(i,0)，上限(i,1)
        //区间B：下限为(j,0)，上限(j,1)
        for (int i = 0; i < rowlen; i++) {
            //被合并了的区间flag置为1，合并别的区间的区间flag设为0
            for (int j = 0; j < rowlen; j++) {
                //(i,0)<=(j,0)<=(i,1), (i,1)<=(j,1), 且区间B尚未被合并，合并后的区间值赋给A
                if (intervals[j][0] >= intervals[i][0] && intervals[j][0] <= intervals[i][1]
                        && intervals[j][1] >= intervals[i][1] && flag[j] == 0){
                    intervals[i][1] = intervals[j][1];
                    flag[j] = 1;
                    flag[i] = 0;
                    continue;
                }
                //(j,0)<=(i,0)<=(i,1)<=(j,1), 且B尚未被合并，合并后的区间值赋给A
                if (intervals[j][0] <= intervals[i][0] && intervals[j][1] >= intervals[i][1] && flag[j] == 0){
                    intervals[i][0] = intervals[j][0];
                    intervals[i][1] = intervals[j][1];
                    flag[j] = 1;
                    flag[i] = 0;
                    continue;
                }
                //(j,0)<=(i,0), (i,0)<=(j,1)<=(i,1), 且B尚未被合并，合并后的区间值赋给A
                if (intervals[j][1] >= intervals[i][0] && intervals[j][1] <= intervals[i][1]
                        && intervals[j][0] <= intervals[i][0] && flag[j] == 0){
                    intervals[i][0] = intervals[j][0];
                    flag[j] = 1;
                    flag[i] = 0;
                    continue;
                }
                //(i,0)<=(j,0)<=(j,1)<=(i,1), 且B尚未被合并，合并后的区间值赋给A
                if (intervals[j][0] >= intervals[i][0] && intervals[j][1] <= intervals[i][1] && flag[j] == 0){
                    flag[j] = 1;
                    flag[i] = 0;
                    continue;
                }
            }
        }
        //System.out.println(Arrays.toString(flag));
        List<Integer> list = new ArrayList();
        for (int i = 0; i < rowlen; i++) {
            if (flag[i] == 0){
                list.add(intervals[i][0]);
                list.add(intervals[i][1]);
            }
        }
        int size = list.size()/2;
        int[][] result = new int[size][2];
        for (int i = 0; i < size; i++) {
            result[i][0] = list.get(2 * i);
            result[i][1] = list.get(2 * i + 1);
        }
        return result;
    }
    public static void main(String[] args) {
        int[][] arr = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        //int[][] arr = {{1,4},{0,1}};
        //int[][] arr = {{2,3},{4,5},{6,7},{8,9},{1,10}};
        System.out.println(Arrays.deepToString(arr));
        System.out.println(Arrays.deepToString(new MergeIntervals().merge(arr)));
    }
}
