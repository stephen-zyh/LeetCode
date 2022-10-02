package BreadthFirstSearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 909
 * @date 2022/9/30 10:33
 */
public class SnakesAndLadders {
    public int snakesAndLadders(int[][] board) {
        //特殊情况，矩阵为空
        if (board == null || board.length == 0){
            return -1;
        }
        int n = board.length;
        int steps = 0;
        boolean[] visited = new boolean[n * n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;
        //BFS
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if (curr == n * n){
                    return steps;   //到达终点，返回最短的步数
                }
                for (int j = 1; j <= 6; j++) {
                    int num = curr + j;
                    //检查是否越界
                    if (num > n * n){
                        break;
                    }
                    //获得对应点的横纵坐标
                    int row = (n - 1) - (num - 1) / n;
                    int col = (n - 1 - row) % 2 == 0 ? (num - 1) % n : (n - 1) - (num - 1) % n;
                    if (!visited[num]){
                        visited[num] = true;    //更新当前节点的遍历状态
                        //分没梯子和有梯子进行讨论
                        if (board[row][col] == -1){
                            queue.offer(num);
                        }else {
                            queue.offer(board[row][col]);
                        }
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    public static void main(String[] args) {
//        int[][] board = {{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,35,-1,-1,13,-1},{-1,-1,-1,-1,-1,-1},{-1,15,-1,-1,-1,-1}};
        int[][] board = {{-1,1,2,-1},{2,13,15,-1},{-1,10,-1,-1},{-1,6,2,8}};
//        int[][] board = {{-1,4,-1},{6,2,6},{-1,3,-1}};
//        int[][] board = {{-1,-1,30,14,15,-1},{23,9,-1,-1,-1,9},{12,5,7,24,-1,30},{10,-1,-1,-1,25,17},{32,-1,28,-1,-1,32},{-1,-1,23,-1,13,19}};
        int result = new SnakesAndLadders().snakesAndLadders(board);
        System.out.println(result);
    }
}
