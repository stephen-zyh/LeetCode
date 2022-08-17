package String;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 8
 * @date 2022/7/11 9:27
 */
public class String2Integer {
    public int myAtoi(String s) {
        int result = 0;
        int signal = 1;
        char[] c = s.toCharArray();
        int pointer = 0;
        //去除前导空格
        while (pointer != c.length){
            if (c[pointer] == ' '){
                pointer++;
            }else {
                break;
            }
        }
        //如果去除前导空格之后没有其他内容了，可以直接返回0
        if (pointer == c.length){
            return 0;
        }
        //确定数的正负
        if (c[pointer] == '-'){
            signal = -1;
            pointer++;
        }else if (c[pointer] == '+'){
            pointer++;
        }
        //如果符号之后紧跟的是一串连续的数字，则遍历求和
        while (pointer != c.length && Character.isDigit(c[pointer])){
            //根据正负分别做越界处理
            if (result > (Integer.MAX_VALUE - (c[pointer] - '0')) / 10){
                if (signal == 1){
                    return Integer.MAX_VALUE;
                }else{
                    return Integer.MIN_VALUE;
                }
            }else{
                result = result * 10 + (c[pointer] - '0');
            }
            pointer++;
        }
        return signal * result;
    }

    public static void main(String[] args) {
//        String s = "   -42";
//        String s = "words and 987";
//        String s = "-91283472332";
        String s = "2147483646";
        System.out.println(new String2Integer().myAtoi(s));
    }
}
