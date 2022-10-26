package DynamicProgramming2D_1DInput;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1246
 * @date 2022/10/26 10:36
 */
public class PalindromeRemoval {
    public int minimumMoves(int[] arr) {
        int len = arr.length;
        int[][] dp = new int[len][len]; //dp[i][j]：i到j区间内最少需要完成的操作数
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                //一个一个地拿出i到j之间的数，因此dp[i][j]最大只可能是j - i + 1
                dp[i][j] = j - i + 1;
            }
        }
        //两个相邻的元素相等，只需要一次操作
        for (int i = 0; i < len - 1; i++) {
            if (arr[i] == arr[i + 1]){
                dp[i][i + 1] = 1;
            }
        }
        //前面完全处理了i到j之间只有两个元素的情况，因此后续的循环从i到j之间有三个元素开始
        for (int i = len - 3; i >= 0; i--) {
            for (int j = i + 2; j < len; j++) {
                //如果arr[i] == arr[j]，移除[i,j]元素的代价不会比[i+1,j-1]更大
                if (arr[i] == arr[j]){
                    dp[i][j] = dp[i + 1][j - 1];
                }
                //尝试从[i,j]中间的k对区间进行分割，即分[i,k]和[k+1,j]两部分进行移除，[i,j]的最小操作次数应该是这其中和最小的
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                }
            }
        }
        return dp[0][len - 1];
    }

    public static void main(String[] args) {
        int[] arr = {1,3,4,1,5};
        int result = new PalindromeRemoval().minimumMoves(arr);
        System.out.println(result);
    }
}
