package BreadthFirstSearch;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 967
 * @date 2022/9/30 19:41
 */
public class NumbersWithSameConsecutiveDifferences {
    public int[] numsSameConsecDiff(int n, int k) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= 9; i++) {
            queue.offer(i);
        }
        int layer = n;
        int size;
        // BFS，因为最后的结果要留在queue中，所以循环条件为layer > 1，每循环一次数字增加一位
        while (layer > 1){
            size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                int reminder = curr % 10;
                if (k == 0){
                    queue.offer(curr * 10 + reminder);
                    continue;
                }
                if (reminder - k >= 0){
                    queue.offer(curr * 10 + reminder - k);
                }
                if (reminder + k <= 9){
                    queue.offer(curr * 10 + reminder + k);
                }
            }
            layer--;
        }
        size = queue.size();
        int[] result = new int[size];
        //获取queue中的结果，注意：循环条件不能写作i < queue.size()，因为这种方式每次循环时都会重新计算size
        for (int i = 0; i < size; i++) {
            result[i] = queue.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 3, k = 7;
        int[] result = new NumbersWithSameConsecutiveDifferences().numsSameConsecDiff(n, k);
        System.out.println(Arrays.toString(result));
    }
}
