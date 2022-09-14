package Graph_DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 305
 * @date 2022/9/10 23:01
 */
public class NumberOfIslandsII {
    int[] directions = {-1, 0, 1, 0,-1};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        //建图，并将图中所有位置初始化为-1
        int[][] grids = new int[m][n];
        for (int[] grid : grids){
            Arrays.fill(grid, -1);
        }
        List<Integer> result = new ArrayList<>();
        int count = 0;
        int islandId = 0;
        for (int[] position : positions){
            //检查该位置四周是否有与之相邻的岛屿
            for (int i = 0; i < 4; i++) {
                int x = position[0] + directions[i];
                int y = position[1] + directions[i + 1];
                //四周的点不在矩阵内或者是海洋，则开始新一轮遍历（检查周围四个点的下一个）
                if (x < 0 || x >= grids.length || y < 0 || y >= grids[0].length || grids[x][y] == -1){
                    continue;
                }
                //周围有与之相邻的岛屿
                if (grids[x][y] != -1){
                    int mark = grids[x][y];
                    if (grids[position[0]][position[1]] == -1){
                        //周围有岛屿且当前点尚未标记，将当前点做上与遍历到的周围的第一个岛屿做上一样的标记，岛屿数目不增不减
                        grids[position[0]][position[1]] = mark;
                    }else if (grids[position[0]][position[1]] != grids[x][y]){
                        //当前点已经被标记，但与周围的某个点ID不同，说明因为当前点变为陆地，使得原来分散的岛屿连成了一片，
                        //因此需将周围的这个点所在岛屿的ID重新标记为当前点的ID，并将岛屿总数减1
                        remarkIsland(x, y, grids, grids[position[0]][position[1]]);
                        count--;
                    }
                    //当前点已经被标记，但与周围的某个点ID相同，说明当前点不止一侧被同一岛屿包围，此时无需再作重新标记
                }
            }
            //该位置四周没有与之相邻的岛屿，说明该岛屿是个新的岛屿，给该岛屿一个新的ID并将岛屿数目加1
            if (grids[position[0]][position[1]] == -1){
                grids[position[0]][position[1]] = islandId++;
                count++;
            }
            result.add(count);
        }
        return result;
    }
    private void remarkIsland(int abscissa, int ordinate, int[][] grids, int mark){
        //base case
        //以下三种情况结束递归：1.该点不在矩阵内；2.该点不是陆地；3.该点已被标记
        if (abscissa < 0 || abscissa >= grids.length || ordinate < 0 || ordinate >= grids[0].length
                || grids[abscissa][ordinate] == -1 || grids[abscissa][ordinate] == mark){
            return;
        }
        //当前点做上标记并继续遍历四周的点
        grids[abscissa][ordinate] = mark;
        for (int i = 0; i < 4; i++) {
            int x = abscissa + directions[i];
            int y = ordinate + directions[i + 1];
            remarkIsland(x, y, grids, mark);
        }
    }

    public static void main(String[] args) {
        int m = 3, n = 3;
        int[][] positions = {{0,0},{0,1},{1,2},{2,1}};
        List<Integer> result = new NumberOfIslandsII().numIslands2(m, n, positions);
        System.out.println(result);
    }
}
