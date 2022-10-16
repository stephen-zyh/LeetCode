package DynamicProgramming1D;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 91
 * @date 2022/10/12 9:34
 */
public class DecodeWays {
    public int numDecodings(String s) {
        if (s.charAt(0) == '0'){
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[0] = 1;

        for (int i = 0; i < len; i++) {
            dp[i + 1] = s.charAt(i) == '0' ? 0 : dp[i]; //只解析当前这一位时
            //如果前一位（作十位）是1或者前一位是2但本位小于等于6时，可以和前一位一起解析
            if (i > 0 && (s.charAt(i - 1) == '1' || (s.charAt(i - 1) == '2' && s.charAt(i) <= '6'))){
                dp[i + 1] += dp[i - 1];
            }
        }
        return dp[len];
    }

    public static void main(String[] args) {
        String s = "12";
        int result = new DecodeWays().numDecodings(s);
        System.out.println(result);
    }
}
