package Backtracking;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 753
 * @date 2022/10/10 16:18
 */
public class CrackingTheSafe {
    int size;
    public String crackSafe(int n, int k) {
        size = (int) Math.pow(k, n);    //最终字符串包含k^n个密码
        StringBuilder builder = new StringBuilder();
        //初值，n位长的0，至于为什么初值是这个，我也不知道，网上找到的解都是这样的，没有一个解释过
        builder.append("0".repeat(Math.max(0, n)));
        Set<String> visit = new HashSet<>();
        visit.add(builder.toString());
        //DFS+回溯
        if (findSequence(k, n, visit, builder)){
            return builder.toString();
        }
        return "";
    }
    boolean findSequence(int k, int n, Set<String> visit, StringBuilder builder){
        //找到结果
        if (visit.size() == size){
            return true;
        }
        //当前字符串要用到n-1位的前缀
        String prefix = builder.substring(builder.length() - (n - 1), builder.length());
        for (int i = 0; i < k; i++) {
            String temp = prefix + i;
            if (!visit.contains(temp)){
                visit.add(temp);
                builder.append(i);
                //若DFS找到结果则返回，否则需要回溯
                if (findSequence(k, n, visit, builder)){
                    return true;
                }
                builder.deleteCharAt(builder.length() - 1);
                visit.remove(temp);
            }
        }
        //所有子路径都没有找到符合要求的结果
        return false;
    }

    public static void main(String[] args) {
        int n = 3, k = 2;
        String result = new CrackingTheSafe().crackSafe(n, k);
        System.out.println(result);
    }
}
