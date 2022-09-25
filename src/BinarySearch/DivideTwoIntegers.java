package BinarySearch;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 29
 * @date 2022/9/17 20:04
 */
public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        //最小值变号后会溢出，取最大值
        if (divisor == -1 && dividend == Integer.MIN_VALUE){
            return Integer.MAX_VALUE;
        }
        int signal = 1;
        //除数为1或者-1的情况不必二分，直接得出结果即可
        if (divisor == -1)  return -dividend;
        if (divisor == 1)   return dividend;
        //确定结果的符号
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)){
            signal = -1;
        }
        //将被除数和除数都转为负数进行运算，避免溢出
        int newDividend = dividend > 0 ? -dividend : dividend;
        int newDivisor = divisor > 0 ? -divisor : divisor;
        if (newDividend > newDivisor)   return 0;
        return signal * div(newDividend, newDivisor);
    }
    private int div(int a, int b){
        if (a > b){
            return 0;
        }
        int temp = b;
        int count = 1;
        //a和b都是小于0的，如果temp大于等于了0，说明溢出了，要结束循环
        while (2 * temp >= a && 2 * temp < 0){
            temp += temp;
            count += count;
        }
        return count + div(a - temp, b);
    }

    public static void main(String[] args) {
        int dividend = 10, divisor = 3;
        int result = new DivideTwoIntegers().divide(dividend, divisor);
        System.out.println(result);
    }
}
