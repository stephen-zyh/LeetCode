package Hash;

import java.util.HashSet;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 356
 * @date 2022/8/10 11:16
 */
public class LineReflection {
    public boolean isReflected(int[][] points) {
        HashSet<String> set = new HashSet<>();
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        //找到最左侧和最右侧的点，并将每个点都加入到set中
        for (int[] point: points) {
            set.add(point[0] + "," + point[1]);
            minX = Math.min(minX, point[0]);
            maxX = Math.max(maxX, point[0]);
        }
        int sum = minX + maxX;
        for (int[] point : points){
            //如果一个点关于对称轴对称的点不在集合中，则返回false，否则返回true
            if (!set.contains((sum - point[0]) + "," + point[1])){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] points = {{1,1}, {1,-1}};
        boolean reflected = new LineReflection().isReflected(points);
        System.out.println(reflected);
    }
}
