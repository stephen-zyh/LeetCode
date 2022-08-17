package Hash;

import java.util.HashMap;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 166
 * @date 2022/8/9 20:24
 */
public class Fraction2RecurringDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder builder = new StringBuilder();
        HashMap<Long, Integer> reminderMap = new HashMap<>();
        //0除以任何非零数都为0
        if (numerator == 0){
            return "0";
        }
        long a = numerator, b = denominator;    //之所以是long是因为(-1, -2147483648)这组恶心的用例
        //判断结果的符号，将两个数字都化为正再参与运算
        if (a < 0 && b < 0){
            a = -a;
            b = -b;
        }else if (a < 0 && b > 0){
            a = -a;
            builder.append("-");
        }else if (a > 0 && b < 0){
            b = -b;
            builder.append("-");
        }
        int bit;    //bit记录小数点后的一位
        //将小数点前的部分添加到builder中
        if (a >= b){
            builder.append(a / b);
            a = a % b;
        }
        //如果经过上述操作，余数为0，不用再考虑小数点后的部分，直接返回即可
        if (a == 0){
            return builder.toString();
        }
        //小数点前没有内容，需要在小数点前添加0
        if (builder.length() == 0){
            builder.append("0");
        }
        builder.append(".");
        //index记录某个答案的某个位在builder中出现的位置
        int index = builder.length();
        while (a != 0){
            //当某个余数再次出现，说明答案是循环小数，在上次出现该余数处插入'('，builder最后加上')'即可返回
            if (reminderMap.containsKey(a)){
                int left = reminderMap.get(a);
                builder.insert(left, "(").append(")");
                return builder.toString();
            }
            //该余数没有出现过，添加到map中并继续计算下一位和下一个余数
            reminderMap.put(a, index++);
            bit = (int) (a * 10 / b);
            a = (a * 10) % b;
            builder.append(bit);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
//        int numerator = 1, denominator = 2;
//        int numerator = 2, denominator = 1;
//        int numerator = 4, denominator = 333;
        int numerator = -1, denominator = -2147483648;
        String result = new Fraction2RecurringDecimal().fractionToDecimal(numerator, denominator);
        System.out.println(result);
    }
}
