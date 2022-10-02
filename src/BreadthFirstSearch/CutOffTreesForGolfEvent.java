package BreadthFirstSearch;

import java.util.*;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 675
 * @date 2022/10/2 10:07
 */
public class CutOffTreesForGolfEvent {
    int[][] grids;
    int row, col;
    public int cutOffTree(List<List<Integer>> forest) {
        row = forest.size();
        col = forest.get(0).size();
        grids = new int[row][col];
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grids[i][j] = forest.get(i).get(j); //建图
                if (grids[i][j] > 1){
                    list.add(new int[]{i, j, grids[i][j]});
                }
            }
        }
        list.sort(Comparator.comparingInt(a -> a[2]));  //按树的高度排序
        int currentX = 0, currentY = 0;
        int result = 0;
        for (int i = 0; i < list.size(); i++) {
            int targetX = list.get(i)[0], targetY = list.get(i)[1];
            //BFS获取下一个最矮的树的最短距离，如果到不了则返回-1，否则将结果加到总步数中去
            int steps = distanceOfNextMinimum(currentX, currentY, targetX, targetY);
            if (steps == -1){
                return -1;
            }
            result += steps;
            //更新起点
            currentX = targetX;
            currentY = targetY;
        }
        return result;
    }

    //BFS获取到目标位置的最短距离
    private int distanceOfNextMinimum(int currAbscissa, int currOrdinate, int distAbscissa, int distOrdinate){
        int[] directions = {-1, 0, 1, 0, -1};
        boolean[][] visit = new boolean[row][col];
        int step = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{currAbscissa, currOrdinate});
        visit[currAbscissa][currOrdinate] = true;
        //BFS
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] currNode = queue.poll();
                //找到目标位置
                if (currNode[0] == distAbscissa && currNode[1] == distOrdinate){
                    return step;
                }
                //检查四周符合要求的点
                for (int j = 0; j < 4; j++) {
                    int x = currNode[0] + directions[j];
                    int y = currNode[1] + directions[j + 1];
                    if (x >= 0 && x < row && y >= 0 && y < col && grids[x][y] != 0 && !visit[x][y]){
                        queue.offer(new int[]{x, y});
                        visit[x][y] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public static void main(String[] args) {
        List<List<Integer>> forest = new ArrayList<>();
        forest.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
        forest.add(new ArrayList<>(Arrays.asList(0, 0, 4)));
        forest.add(new ArrayList<>(Arrays.asList(7, 6, 5)));
        int result = new CutOffTreesForGolfEvent().cutOffTree(forest);
        System.out.println(result);
    }
}
