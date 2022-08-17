package String;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 6, 本题重在发现排列规律
 * @date 2022/7/10 10:07
 */
public class ZigZagConversion {
    public String convert(String s, int numRows) {
        //如果numRows为1，可以直接返回原数组
        if (numRows == 1){
            return s;
        }
        char[] ch = s.toCharArray();
        int n = numRows - 1;
        int len = s.length();
        StringBuilder result = new StringBuilder();
        //添加Z字排列结果的第一行
        for (int j = 0; j < len; j++) {
            if (j % (2 * n) == 0){
                result.append(ch[j]);
            }
        }
        //添加中间n-1行
        for (int i = 1; i < numRows - 1; i++) {
            for (int j = 0; j < len; j++) {
                if (j % (2 * n) == i){
                    result.append(ch[j]);
                }
                if (j % (2 * n) == 2 * n - i){
                    result.append(ch[j]);
                }
            }
        }
        //添加最后一行
        for (int j = 0; j < len; j++) {
            if (j % (2 * n) == n){
                result.append(ch[j]);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
//        String s = "PAYPALISHIRING";
//        int numRows = 3;
        String s = "A";
        int numRows = 1;
        System.out.println(new ZigZagConversion().convert(s, numRows));
    }
}
