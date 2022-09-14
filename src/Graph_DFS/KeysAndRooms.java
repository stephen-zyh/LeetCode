package Graph_DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 841
 * @date 2022/9/12 17:10
 */
public class KeysAndRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visit = new boolean[rooms.size()];
        visitRooms(0, rooms, visit);
        for (int i = 0; i < visit.length; i++) {
            //只要有一个房间没被访问过就返回false
            if (!visit[i]){
                return false;
            }
        }
        return true;
    }

    private void visitRooms(int currRoom, List<List<Integer>> table, boolean[] visit){
        //base case, 已访问的房间无需再次访问
        if (visit[currRoom]){
            return;
        }
        visit[currRoom] = true; //访问标志位设为true
        //用从该房间拿到的钥匙继续访问对应房间
        for (int neighbor : table.get(currRoom)){
            visitRooms(neighbor, table, visit);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(Arrays.asList(1,3));
        rooms.add(Arrays.asList(3,0,1));
        rooms.add(Arrays.asList(2));
        rooms.add(Arrays.asList(0));
        boolean result = new KeysAndRooms().canVisitAllRooms(rooms);
        System.out.println(result);
    }
}
