package BinarySearch;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 50
 * @date 2022/9/14 17:34
 */
public class Pow {
    public double myPow(double x, int n) {
        //n为0和1是base case，
        if (n == 0){
            return 1;
        }
        if (n == 1){
            return x;
        }
        //根据n的奇偶决定函数递归调用的形式
        if ((n & 1) == 0){
            return myPow(x * x, n / 2);
        }else {
            //根据奇数次方的正负决定函数递归调用的形式
            return (n > 0 ? x : 1.0 / x) * myPow(x * x, n / 2);
        }
    }

    public static void main(String[] args) {
        double x = 2;
        int n = 10;
        double result = new Pow().myPow(x, n);
        System.out.println(result);
    }
}
