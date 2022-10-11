package Backtracking;

import java.util.*;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1096，leetcode 1087的相似题目，但我实在想不到怎么用回溯的方法解决这个题，并且该题的标签也没有回溯，索性BFS
 * @date 2022/10/10 9:13
 */
public class BraceExpansionII {
    public List<String> braceExpansionII(String expression) {
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        StringBuilder builder = new StringBuilder();
        queue.offer(expression);
        while (!queue.isEmpty()){
            String curr = queue.poll();
            //不包含括号的字符串是一个潜在的答案，将其添加到结果集中
            if (!curr.contains("{")){
                    set.add(curr);
                continue;
            }
            int i = 0;
            int left = 0, right;
            //找到当前字符串最里层的括号，其中left和right分别对应左右括号的索引
            while (curr.charAt(i) != '}'){
                if (curr.charAt(i) == '{'){
                    left = i;
                }
                i++;
            }
            right = i;

            String before = curr.substring(0, left);
            String after = curr.substring(right + 1);
            //将括号中的内容用逗号分割，方便与括号前后的内容进行拼接
            String[] strs = curr.substring(left + 1, right).split(",");
            //拼接
            for (String str : strs){
                builder.setLength(0);
                builder.append(before).append(str).append(after);
                queue.offer(builder.toString());
            }
        }
        //将set中的结果加入到result中，此时result不包含重复元素
        List<String> result = new ArrayList<>(set);
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        String expression = "abcabcabc";
        List<String> result = new BraceExpansionII().braceExpansionII(expression);
        System.out.println(result);
    }
}
