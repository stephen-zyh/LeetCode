package Heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 253
 * @date 2022/8/25 20:56
 */
public class MeetingRoomsII {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0){
            return 0;
        }
        //优先级队列按照结束时间升序排列，区间按照开始时间升序排列
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o));
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        queue.offer(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            //当前遍历到的会议开始时间比最早结束的会议的结束时间要晚，可使用原有会议室；否则添加新的会议室
            if (intervals[i][0] >= queue.peek()){
                queue.poll();
            }
            queue.offer(intervals[i][1]);
        }
        return queue.size();
    }

    public static void main(String[] args) {
        int[][] intervals = {{0, 30},{5, 10},{15, 20}};
        int result = new MeetingRoomsII().minMeetingRooms(intervals);
        System.out.println(result);
    }
}
