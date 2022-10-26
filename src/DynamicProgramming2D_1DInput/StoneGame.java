package DynamicProgramming2D_1DInput;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 877，根据博弈论先手必胜，所以直接return true也没有任何毛病
 * @date 2022/10/25 20:19
 */
public class StoneGame {
    public boolean stoneGame(int[] piles) {
        int len = piles.length;
        int[][] dp = new int[len][len]; //dp[i][j]：在[i,j]堆石子里，先手比后手能获得的优势
        for (int i = 0; i < len; i++) {
            dp[i][i] = piles[i];
        }
        //由于求dp[i][j]时要求dp[i + 1][j]和dp[i][j - 1]已知，因此i从右往左遍历，j从左往右遍历
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                //先手每一次可以拿第一堆或者最后一堆
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        //如果先手最后的优势大于0则胜利，反之失败
        return dp[0][len - 1] > 0;
    }

    public static void main(String[] args) {
        int[] piles = {5,3,4,5};
        boolean result = new StoneGame().stoneGame(piles);
        System.out.println(result);
    }
}
