package DynamicProgramming_Knapsack;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 474，参考leetcode 416
 * @date 2022/10/28 18:43
 */
public class OnesAndZeroes {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1]; //dp[i][j]：共有i个0和j个1的序列最大长度
        int result = 0;
        for(String str : strs){
            int countOne = countOne(str);
            int countZero = str.length() - countOne;
            //0或1的数目过多，不可添加，直接跳过
            if (countZero > m || countOne > n){
                continue;
            }
            //对于每一个str都从后往前遍历，保证dp[i][j]的基础上添加str不会影响到dp[i + countZero][j + countOne]添加str（即防止同一字符串添加多次）
            for (int i = m; i >= countZero; i--) {
                for (int j = n; j >= countOne; j--) {
                    if (dp[i - countZero][j - countOne] != 0){
                        dp[i][j] = Math.max(dp[i][j], dp[i - countZero][j - countOne] + 1);
                        result = Math.max(dp[i][j], result);    //更新最长序列的长度
                    }
                }
            }
            //最后更新dp[countZero][countOne]，保证dp[countZero][countOne]的基础上添加str不会影响到dp[2*countZero][2*countOne]添加str
            dp[countZero][countOne] = Math.max(dp[countZero][countOne], 1);
            result = Math.max(dp[countZero][countOne], result);
        }
        return result;
    }

    //统计字符串中字符'1'出现的次数
    private int countOne(String str){
        int length = str.length();
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) == '1'){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String[] strs = {"10","0001","111001","1","0"};
        int m = 5, n = 3;
        int result = new OnesAndZeroes().findMaxForm(strs, m, n);
        System.out.println(result);
    }
}
