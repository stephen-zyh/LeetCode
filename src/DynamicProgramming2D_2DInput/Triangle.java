package DynamicProgramming2D_2DInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 120
 * @date 2022/10/18 19:55
 */
public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        int[] dp = new int[row];
        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < row; i++) {
            int col = triangle.get(i).size();
            for (int j = col - 1; j >= 0; j--) {
                if (j == col - 1){
                    //本行最后一列只可能由上一行最后一列到达
                    dp[j] = dp[j - 1] + triangle.get(i).get(j);
                }else if (j == 0){
                    //本行第一列只能由上一行第一列到达
                    dp[j] += triangle.get(i).get(0);
                }else {
                    //第i行j列由第i-1行j列或第i-1行j-1列到达
                    dp[j] = Math.min(dp[j - 1], dp[j]) + triangle.get(i).get(j);
                }
            }
        }
        int result = dp[0];
        //寻找最小路径和
        for (int i = 1; i < row; i++) {
            if (dp[i] < result){
                result = dp[i];
            }
        }
        return result;
    }
    public int minimumTotal2(List<List<Integer>> triangle) {
        //一种更优的解法，自底向上遍历
        int n = triangle.size();
        int [] dp = new int [n];
        for(int i = 0 ; i < n ; i++){
            //初值设为最后一行的值
            dp[i] = triangle.get(n-1).get(i);
        }
        //从下往上走
        for(int i = n-2 ; i >= 0 ; i--){
            for(int j = 0 ; j <= i ; j++){
                //第i行j列只能由正下方或者右下方到达
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j] , dp[j+1]);
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(new ArrayList<>(Arrays.asList(2)));
        triangle.add(new ArrayList<>(Arrays.asList(3,4)));
        triangle.add(new ArrayList<>(Arrays.asList(6,5,7)));
        triangle.add(new ArrayList<>(Arrays.asList(4,1,8,3)));
        int result = new Triangle().minimumTotal(triangle);
        System.out.println(result);
    }
}
