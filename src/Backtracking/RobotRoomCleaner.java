package Backtracking;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 489
 * @date 2022/10/10 11:31
 */
public class RobotRoomCleaner {
    int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
    Set<String> visit = new HashSet<>();    //确定哪些地方已经清扫过
    public void cleanRoom(Robot robot) {
        visit.add("0-0");   //以当前位置为(0,0)开始清扫
        doClean(robot, 0, 0, 0);
    }

    private void doClean(Robot robot, int currX, int currY, int currDir){
        robot.clean();  //清理当前位置
        //清理四周未清扫的位置
        for (int i = 1; i <= 4; i++) {
            robot.turnRight();  //每一个方向清扫完后要右转
            int nextDir = (currDir + i) % 4;    //确定下一个方向
            //确定下一个位置的坐标
            int x = currX + directions[nextDir][0];
            int y = currY + directions[nextDir][1];
            String next = x + "-" + y;
            //如果还没清扫则清扫
            if (!visit.contains(next) && robot.move()){
                visit.add(next);    //标记当前位置
                doClean(robot, x, y, nextDir);  //以当前位置为起点深度优先
                //两次左转即为掉头
                robot.turnLeft();
                robot.turnLeft();
                robot.move();   //回到上一个节点处
                //再次掉头，回到原来的方向
                robot.turnRight();
                robot.turnRight();
            }
        }
    }
}
