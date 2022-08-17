package Hash;

import java.util.*;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 554
 * @date 2022/8/10 16:55
 */
public class BrickWall {
    public int leastBricks(List<List<Integer>> wall) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int rowTotal = 0;
        for (List<Integer> row : wall) {
            int sum = 0;
            for (Integer brick : row) {
                //记录sum处“砖缝”的个数
                sum += brick;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
            rowTotal = sum;
        }
        int height = wall.size();
        int least = height;
        for(Integer key : map.keySet()){
            //墙体最右侧的缝不纳入计算，穿过砖头的最小数目应该为墙的高度height减去key位置上“砖缝”的数目的最小值
            if (key != rowTotal){
                least = Math.min(least, height - map.get(key));
            }
        }
        return least;
    }

    public static void main(String[] args) {
        ArrayList<List<Integer>> wall = new ArrayList<>();
        wall.add(new ArrayList<>(Arrays.asList(1,2,2,1)));
        wall.add(new ArrayList<>(Arrays.asList(3,1,2)));
        wall.add(new ArrayList<>(Arrays.asList(1,3,2)));
        wall.add(new ArrayList<>(Arrays.asList(2,4)));
        wall.add(new ArrayList<>(Arrays.asList(3,1,2)));
        wall.add(new ArrayList<>(Arrays.asList(1,3,1,1)));
        int leastBricks = new BrickWall().leastBricks(wall);
        System.out.println(leastBricks);
    }
}
