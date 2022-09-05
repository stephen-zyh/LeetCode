package Graph_BreadthFS;

import java.util.*;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1129
 * @date 2022/9/3 15:47
 */
public class ShortestPathWithAlternatingColors {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        //red, blue的每一项存储一个节点的邻居节点
        List<List<Integer>> red = new ArrayList<>();
        List<List<Integer>> blue = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            red.add(new ArrayList<>());
            blue.add(new ArrayList<>());
        }
        for (int[] redEdge : redEdges){
            red.get(redEdge[0]).add(redEdge[1]);
        }
        for (int[] blueEdge : blueEdges){
            blue.get(blueEdge[0]).add(blueEdge[1]);
        }
        //标记一个节点是否访问过
        boolean[] redVisit = new boolean[n];
        boolean[] blueVisit = new boolean[n];

        Queue<int[]> queue = new LinkedList<>();
        //int[]中三项分别为节点、颜色以及与0的距离，color的0表示红色，1表示蓝色
        queue.offer(new int[]{0, 1, 0});
        queue.offer(new int[]{0, 0, 0});

        redVisit[0] = true;
        blueVisit[0] = true;

        int[] result = new int[n];
        Arrays.fill(result, -1);    //全部初始化为-1
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int node = curr[0];
                int color = curr[1];
                int distance = curr[2];
                //result[node]还没有被更新过，说明distance是到node的最短距离
                if (result[node] == -1){
                    result[node] = distance;
                }
                if (color == 0){
                    //该边为红色，其后应该为以next为起点的蓝边
                    for(int next : red.get(node)){
                        if (!blueVisit[next]){
                            blueVisit[next] = true;
                            queue.offer(new int[]{next, 1, distance + 1});  //改变颜色，增加距离
                        }
                    }
                }else {
                    //与if分支同理
                    for(int next : blue.get(node)){
                        if (!redVisit[next]){
                            redVisit[next] = true;
                            queue.offer(new int[]{next, 0, distance + 1});
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] redEdges = {{0,1},{1,2}}, blueEdges = {};
        int[] result = new ShortestPathWithAlternatingColors().shortestAlternatingPaths(n, redEdges, blueEdges);
        System.out.println(Arrays.toString(result));
    }
}
