package DynamicProgramming1D_MultipleStates;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 552
 * @date 2022/10/18 11:35
 */
public class StudentAttendanceRecordII {
    private static final int MOD= 1000000007;
    public int checkRecord(int n) {
        long[][][] dp = new long[n + 1][2][3];
        dp[1][0][0] = 1;
        dp[1][0][1] = 1;
        dp[1][1][0] = 1;
        for (int i = 2; i <= n; i++) {
            //加P
            //长度为i时0A0L：0A0L+0A1L+0A2L（长度为i-1时）
            dp[i][0][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % MOD;
            //长度为i时1A0L：1A0L+1A1L+1A2L（长度为i-1时）
            dp[i][1][0] = (dp[i - 1][1][0] + dp[i - 1][1][1] + dp[i - 1][1][2]) % MOD;
            //加L
            //能添加L的情况有四种：0A0L、1A0L、0A1L、1A1L
            dp[i][0][1] = dp[i - 1][0][0];
            dp[i][1][1] = dp[i - 1][1][0];
            dp[i][0][2] = dp[i - 1][0][1];
            dp[i][1][2] = dp[i - 1][1][1];
            //加A
            //长为i-1的序列为0A0L/1L/2L
            //注意：dp[i][1][0]在加P时已经修改过，这里是累加，而不是直接赋值
            dp[i][1][0] += (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % MOD;
        }
        //各种可能结果求和为结果
        return (int) ((dp[n][0][0] + dp[n][0][1] + dp[n][0][2] + dp[n][1][0] + dp[n][1][1] + dp[n][1][2]) % MOD);
    }

    public static void main(String[] args) {
        int n = 10101;
        int result = new StudentAttendanceRecordII().checkRecord(n);
        System.out.println(result);
    }
}
