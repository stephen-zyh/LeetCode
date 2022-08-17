package Hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 133
 * @date 2022/8/9 10:00
 */
public class CloneGraph {
    HashMap<Integer, Node> map = new HashMap<>();   //储存节点值到克隆节点之间的映射
    public Node cloneGraph(Node node) {
        if (node == null){
            return null;
        }
        Node clone = new Node(node.val);
        dfs(node, clone);
        return clone;
    }

    private void dfs(Node origin, Node clone){
        map.put(origin.val, clone);
        for (Node neighbor : origin.neighbors) {
            //如果该邻居已经经过dfs遍历拷贝过，则不用再对其dfs，直接将其加入到clone的邻居节点即可
            if (map.containsKey(neighbor.val)){
                clone.neighbors.add(map.get(neighbor.val));
            }else {
                //否则对该邻居节点进行dfs遍历，并将其加入到clone的邻居节点
                Node copy_neighbor = new Node(neighbor.val);
                dfs(neighbor, copy_neighbor);
                clone.neighbors.add(copy_neighbor);
            }
        }
    }

    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
