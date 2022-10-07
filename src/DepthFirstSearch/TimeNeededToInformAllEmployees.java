package DepthFirstSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1376
 * @date 2022/10/5 10:45
 */
public class TimeNeededToInformAllEmployees {
    Map<Integer, List<Integer>> map = new HashMap<>();
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        //建图
        for (int i = 0; i < n; i++) {
            if (manager[i] != -1){
                map.putIfAbsent(manager[i], new ArrayList<>());
                map.get(manager[i]).add(i);
            }
        }
        return getTime(headID, informTime);
    }
    //获取通知所有下属所需要的时间
    private int getTime(int headID, int[] informTime){
        //base case，不是领导（是叶子节点），返回0
        if (!map.containsKey(headID)){
            return 0;
        }
        int result = 0;
        //通知下属所需要的时间应当是通知到所有下属分支的时间的最大值
        for (int subordinate : map.get(headID)){
            result = Math.max(result, getTime(subordinate, informTime) + informTime[headID]);
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 15;
        int headID = 0;
        int[] manager = {-1,0,0,1,1,2,2,3,3,4,4,5,5,6,6};
        int[] informTime = {1,1,1,1,1,1,1,0,0,0,0,0,0,0,0};
        int result = new TimeNeededToInformAllEmployees().numOfMinutes(n, headID, manager, informTime);
        System.out.println(result);
    }
}
