package Stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 636
 * @date 2022/7/30 16:21
 */
public class ExclusiveTimeOfFunctions {
    public int[] exclusiveTime(int n, List<String> logs) {
        Stack<Log> stack = new Stack<>();
        int[] result = new int[n];
        for (String s : logs) {
            Log log = new Log(s);
            int time = log.getTime();
            int id = log.getID();
            if (log.isStart()){
                stack.push(log);
            }else {
                Log top = stack.pop();
                result[id] += time - top.getTime() + 1;
                if (!stack.isEmpty()){
                    result[stack.peek().getID()] -= (time - top.getTime() + 1);
                }
            }
        }
        return result;
    }

    private static class Log{
        String[] log;
        private Log(String s){
            log = s.split(":");
        }

        int getID(){
            return Integer.parseInt(log[0]);
        }

        boolean isStart(){
            return log[1].equals("start");
        }

        int getTime(){
            return Integer.parseInt(log[2]);
        }
    }

    public static void main(String[] args) {
        int n = 2;
        List<String> logs = Arrays.asList("0:start:0","1:start:2","1:end:5","0:end:6");
        int[] result = new ExclusiveTimeOfFunctions().exclusiveTime(n, logs);
        System.out.println(Arrays.toString(result));
    }
}
