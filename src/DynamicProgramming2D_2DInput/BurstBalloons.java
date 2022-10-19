package DynamicProgramming2D_2DInput;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 312
 * @date 2022/10/19 12:27
 */
public class BurstBalloons {
    public int maxCoins(int[] nums) {
        int len = nums.length;
        //在原数组首尾添加两个1
        int[] points = new int[len + 2];
        points[0] = 1;
        points[len + 1] = 1;
        System.arraycopy(nums, 0, points, 1, len);
        int[][] dp = new int[len + 2][len + 2];
        /*
        要求得在开区间(i,j)内能获得的最大硬币数，就要考虑最后扎破哪一个气球
        假设最后扎破的气球的索引是k，那么要求得在此条件下获得的收益，就要求开区间(i,k)和(k,j)的最大收益是已经求得的。这里有两个问题：
        一、为什么要求开区间(i,k)和(k,j)有最大收益呢？
        因为戳破开区间(i,k)和(k,j)内的气球和戳破索引为k的气球是三个相互独立的子问题，开区间(i,k)和(k,j)收益尽可能大，最终收益才可能大。
        二、如何保证开区间(i,k)和(k,j)收益在求(i,j)之前已经求得呢？
        保证列相同的情况下，行数大的比行数小的先遍历，如(k,j)和(i,j)；保证行相同的情况下，列数小的比列数大的先遍历，如(i,k)和(i,j)。
        因此横坐标从最大值递减，纵坐标从0开始递增
        随着最后戳破的k不同，开区间(i,j)的收益有不同取值，取其中最大的作为最大收益
        */
        for (int i = len; i >= 0; i--) {
            for (int j = i + 1; j < len + 2; j++) {
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + points[i] * points[k] * points[j] + dp[k][j]);
                }
            }
        }
        return dp[0][len + 1];
    }

    public static void main(String[] args) {
        int[] nums = {3,1,5,8};
        int result = new BurstBalloons().maxCoins(nums);
        System.out.println(result);
    }
}
