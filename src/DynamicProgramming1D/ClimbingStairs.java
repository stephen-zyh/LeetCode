package DynamicProgramming1D;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 70，使用递归会超时，因为递归过程中并没有保存数据，导致重复计算
 * @date 2022/10/11 20:01
 */
public class ClimbingStairs {
    public int climbStairs(int n) {
        if (n == 1 || n == 2){
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            //走到当前所在位置必须先走到一步前或两步前，因此走到当前位置的方法数是走到一步前和两步前的方法的总和
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 45;
        int result = new ClimbingStairs().climbStairs(n);
        System.out.println(result);
    }
}
