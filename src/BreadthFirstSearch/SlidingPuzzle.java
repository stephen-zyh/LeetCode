package BreadthFirstSearch;

import java.util.*;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 773
 * @date 2022/10/2 16:42
 */
public class SlidingPuzzle {
    public int slidingPuzzle(int[][] board) {
        String goal = "123450";
        StringBuilder builder = new StringBuilder();
        Map<String, Integer> map = new HashMap<>(); //建立字符串和其中0的索引的映射
        Set<String> set = new HashSet<>();  //存放已经遍历过的可能
        int index = 0;
        //将起始状态转化为字符串，并记录下0的位置
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                builder.append(board[i][j]);
                if (board[i][j] == 0){
                    index = i * 3 + j;
                }
            }
        }
        String start = builder.toString();
        if (start.equals(goal)){
            return 0;
        }
        map.put(start, index);
        //neighbors是每一个位置上的元素的邻居的索引
        int[][] neighbors = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
        int step = 0;
        //BFS
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        set.add(start);
        while (!queue.isEmpty()){
            int size = queue.size();
            step++;
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                index = map.get(curr);
                for (int next : neighbors[index]){
                    String child = getChild(curr, index, next); //0和其邻居交换位置
                    //找到目标，返回总步数
                    if (child.equals(goal)){
                        return step;
                    }
                    //该可能没有被遍历过，入队、修改遍历状态，并建立映射
                    if (!set.contains(child)){
                        queue.offer(child);
                        set.add(child);
                        map.put(child, next);
                    }
                }
            }
        }
        return -1;
    }

    //原字符串相应的两个位置交换对应的字符
    private String getChild(String current, int original, int nearby){
        char[] array = current.toCharArray();

        char origin = array[original];
        array[original] = array[nearby];
        array[nearby] = origin;

        return String.valueOf(array);
    }

    public static void main(String[] args) {
        int[][] board = {{4,1,2},{5,0,3}};
        int result = new SlidingPuzzle().slidingPuzzle(board);
        System.out.println(result);
    }
}
