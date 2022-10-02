package BreadthFirstSearch;

import java.util.*;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 752
 * @date 2022/10/1 10:24
 */
public class OpenTheLock {
    public int openLock(String[] deadends, String target) {
        Set<String> set = new HashSet<>(Arrays.asList(deadends));
        //特殊情况
        if (set.contains("0000")){
            return -1;
        }
        Set<String> visit = new HashSet<>();
        //BFS，每一步改变一个位
        int steps = 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        visit.add("0000");  //避免重复访问，造成死循环
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                //找到最优解
                if (curr.equals(target)){
                    return steps;
                }
                char[] array = curr.toCharArray();
                for (int j = 0; j < array.length; j++) {
                    char origin = array[j]; //保存本位上的字符
                    //对于锁的每一位，都有向前拨和向后拨两种情况，如果是之前没有遍历到的，则入队，作为下一步
                    array[j] = (char) (origin - 1) < '0' ? (char) (origin + 9) : (char) (origin - 1);
                    String neighbor1 = String.valueOf(array);
                    if (!set.contains(neighbor1) && !visit.contains(neighbor1)){
                        queue.offer(neighbor1);
                        visit.add(neighbor1);
                    }

                    array[j] = (char) (origin + 1) > '9' ? (char) (origin - 9) : (char) (origin + 1);
                    String neighbor2 = String.valueOf(array);
                    if (!set.contains(neighbor2) && !visit.contains(neighbor2)){
                        queue.offer(neighbor2);
                        visit.add(neighbor2);
                    }
                    //改回原来的序列
                    array[j] = origin;
                }
            }
            steps++;
        }
        //找不到最优解
        return -1;
    }

    public static void main(String[] args) {
        String[] deadends = {"0201","0101","0102","1212","2002"};
        String target = "0202";
        int result = new OpenTheLock().openLock(deadends, target);
        System.out.println(result);
    }
}
