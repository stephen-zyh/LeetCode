package BinarySearch;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 69
 * @date 2022/9/14 9:48
 */
public class Sqrt {
    public int mySqrt(int x) {
        //区间长度小于等于1的情况
        if (x == 0 || x == 1){
            return x;
        }
        int left = 1, right = x / 2;
        while (left < right){
            int middle = (left + right + 1) / 2;    //之所以加1是为了上取整，避免迭代到区间长度为1时陷入死循环
            if (middle > x / middle){
                //写成middle > x / middle防止溢出
                right = middle - 1;
            }else {
                left = middle;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int x = 2147483647;
        int result = new Sqrt().mySqrt(x);
        System.out.println(result);
    }
}
