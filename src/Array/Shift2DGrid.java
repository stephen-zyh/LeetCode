package Array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1260
 * @date 2022/7/2 16:42
 */
public class Shift2DGrid {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int rowlen = grid.length;
        int collen = grid[0].length;
        int size = collen * rowlen;
        int[] arr = new int[size];
        for (int i = 0; i < rowlen; i++) {
            for (int j = 0; j < collen; j++) {
                arr[(i * collen + j + k) % size] = grid[i][j];
            }
        }
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < rowlen; i++) {
            list.add(new ArrayList<>());
            for (int j = 0; j < collen; j++) {
                list.get(i).add(arr[i * collen + j]);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[][] grid = {{1,2,3},{4,5,6},{7,8,9}};
        List<List<Integer>> result = new Shift2DGrid().shiftGrid(grid, 12);
        for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i).toString());
        }
    }
}
