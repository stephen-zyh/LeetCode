package DynamicProgramming1D;

import java.util.Arrays;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 338
 * @date 2022/10/15 19:13
 */
public class CountingBits {
    public int[] countBits(int n) {
        int[] result = new int[n + 1];
        result[0] = 0;
        for (int i = 1; i < result.length; i++) {
            if ((i & 1) == 1){
                //奇数的1的个数应该比其前一个偶数多1
                result[i] = result[i - 1] + 1;
            }else {
                //偶数二进制的最后一位必定为0，因此i和i/2的1的个数相同
                result[i] = result[i / 2];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 5;
        int[] result = new CountingBits().countBits(n);
        System.out.println(Arrays.toString(result));
    }
}
