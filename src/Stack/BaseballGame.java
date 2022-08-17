package Stack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 682
 * @date 2022/7/27 6:52
 */
public class BaseballGame {
    public int calPoints(String[] ops) {
        int sum = 0;
        List<Integer> list = new ArrayList<>(); //用动态数组模拟栈
        for (String op : ops) {
            switch (op) {
                case "+" -> {
                    list.add(list.get(list.size() - 1) + list.get(list.size() - 2));
                    sum += list.get(list.size() - 1);   //size在上一行操作后已变
                }
                case "C" -> {
                    sum -= list.get(list.size() - 1);   //与case "+"同理
                    list.remove(list.size() - 1);
                }
                case "D" -> {
                    list.add(list.get(list.size() - 1) * 2);
                    sum += list.get(list.size() - 1);
                }
                default -> {
                    list.add(Integer.parseInt(op));
                    sum += list.get(list.size() - 1);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
//        String[] ops = {"5","2","C","D","+"};
        String[] ops = {"5","-2","4","C","D","9","+","+"};
        int result = new BaseballGame().calPoints(ops);
        System.out.println(result);
    }
}
