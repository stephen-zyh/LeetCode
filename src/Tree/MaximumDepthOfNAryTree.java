package Tree;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 559
 * @date 2022/8/12 9:24
 */
public class MaximumDepthOfNAryTree {
    public int maxDepth(Node root) {
        //base case
        if (root == null){
            return 0;
        }
        int depth = 0;
        if (root.children != null){
            for (Node child : root.children) {
                //对于每个子节点递归调用并取最大者
                depth = Math.max(depth, maxDepth(child));
            }
        }
        //当前节点最大深度是子节点的最大深度加1
        return depth + 1;
    }

    public static void main(String[] args) {
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n3 = new Node(3, new ArrayList<>(Arrays.asList(n5, n6)));
        Node n2 = new Node(2);
        Node n4 = new Node(4);
        Node n1 = new Node(1, new ArrayList<>(Arrays.asList(n3, n2, n4)));

        int result = new MaximumDepthOfNAryTree().maxDepth(n1);
        System.out.println(result);
    }
}
