package DynamicProgramming1D;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 96
 * @date 2022/10/14 9:57
 */
public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        //一共i个节点，根节点一个，左侧j个，右侧就有(i - 1 - j)个，对于确定的i、j，可能的结果数为dp[j] * dp[i - 1 - j]中
        //在i不变的情况下，改变左侧的节点数，即j的值，将不同的dp[j] * dp[i - 1 - j]累加，即为i个节点的所有可能
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += (dp[j] * dp[i - 1 - j]);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 3;
        int result = new UniqueBinarySearchTrees().numTrees(n);
        System.out.println(result);
    }
}
