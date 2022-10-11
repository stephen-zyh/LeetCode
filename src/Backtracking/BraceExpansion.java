package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1087
 * @date 2022/10/8 17:23
 */
public class BraceExpansion {
    public String[] expand(String S) {
        List<String> list = new ArrayList<>();
        int len = S.length();
        //对原来的字符串进行预处理，将每个'{''}'中的字母视作一个字符串，其外的也视为若干个字符串
        //题目相当于在字符串数组的每个字符串中挑选一个字符进行组合，构成结果
        for (int i = 0; i < len; i++) {
            if (S.charAt(i) == '{'){
                int j = i + 1;
                StringBuilder builder = new StringBuilder();
                while (j < len && S.charAt(j) != '}'){
                    if (S.charAt(j) == ','){
                        j++;
                        continue;
                    }
                    builder.append(S.charAt(j));
                    j++;
                }
                list.add(builder.toString());
                i = j;
            }else {
                list.add(S.charAt(i) + "");
            }
        }
        List<String> res = new ArrayList<>();
        int size = list.size();
        generateString(list, 0, new StringBuilder(), res, size);
        String[] result = new String[res.size()];
        //列表转数组
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        //题目要求结果以字典序返回
        Arrays.sort(result);
        return result;
    }

    private void generateString(List<String> list, int index, StringBuilder builder, List<String> result, int listSize){
        //从字符串数组的每一个字符串中都选择了字符出来了，构成了一个答案，将其添加到结果中
        if (builder.length() == listSize){
            result.add(builder.toString());
            return;
        }
        String current = list.get(index);
        int len = current.length();
        for (int i = 0; i < len; i++) {
            //回溯，在每个字符串中选择一个字符出来
            builder.append(current.charAt(i));
            generateString(list, index + 1, builder, result, listSize);
            builder.deleteCharAt(builder.length() - 1);
        }
    }

    public static void main(String[] args) {
        String s = "{a,b}c{d,e}f";
        String[] result = new BraceExpansion().expand(s);
        System.out.println(Arrays.toString(result));
    }
}
