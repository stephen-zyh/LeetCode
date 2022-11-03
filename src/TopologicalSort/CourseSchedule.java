package TopologicalSort;

import java.util.*;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 207
 * @date 2022/11/2 16:39
 */
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();  //记录图中各个节点与其指向的所有节点之间的映射
        int[] inDegree = new int[numCourses];   //记录每个节点的入度
        for (int i = 0; i < numCourses; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites){
            int end = prerequisite[0], start = prerequisite[1];
            map.get(start).add(end);
            inDegree[end]++;
        }
        //BFS，维护一个队列，队列中是入度为0的节点
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0){
                queue.offer(i);
            }
        }
        int count = 0;
        while (!queue.isEmpty()){
            int curr = queue.poll();
            //每次删除一个入度为0的节点，并更新其neighbor的入度信息，如果某个neighbor的入度变为0，将其入队
            for (int neighbor : map.get(curr)){
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0){
                    queue.offer(neighbor);
                }
            }
            count++;    //统计删除的节点数
        }
        //如果全部节点都能删完，说明图中没有环，能按照一定顺序把课程修完；反之则不能
        return count == numCourses;
    }

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1,0},{0,1}};
        boolean result = new CourseSchedule().canFinish(numCourses, prerequisites);
        System.out.println(result);
    }
}
