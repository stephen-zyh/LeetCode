package DepthFirstSearch;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 394，在Stack中有另外的题解
 * @date 2022/10/4 16:40
 */
public class DecodeString {
    int index;
    public String decodeString(String s) {
        return decode(s, 0);
    }
    private String decode(String s, int start){
        StringBuilder builder = new StringBuilder();
        int num = 0;
        for (int i = start; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (curr >= '0' && curr <= '9'){
                num = num * 10 + (curr - '0');
            }else if (curr == '['){
                //DFS
                String result = decode(s, i + 1);
                i = index;
                //将后面的字符串重复对应的次数，并将num重新置0
                builder.append(result.repeat(Math.max(0, num)));
                num = 0;
            }else if (curr == ']'){
                index = i;  //内层解析完毕，从'['到']'的索引不用再遍历，故将当前i的值赋给index，下次遍历从index+1开始
                return builder.toString();
            }else {
                //是普通字母，正常添加
                builder.append(curr);
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String s = "3[a]2[bc]";
        String result = new DecodeString().decodeString(s);
        System.out.println(result);
    }
}
