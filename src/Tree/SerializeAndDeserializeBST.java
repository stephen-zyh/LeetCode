package Tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 449
 * @date 2022/8/19 10:54
 */
public class SerializeAndDeserializeBST {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder bulider = new StringBuilder();
        encode(root, bulider);
        return bulider.toString();
    }

    private void encode(TreeNode root, StringBuilder builder){
        if (root == null){
            return;
        }
        //先序遍历，将节点值保存到builder中
        builder.append(root.val).append(",");
        encode(root.left, builder);
        encode(root.right, builder);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()){
            return null;
        }
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return decode(queue, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode decode(Queue<String> queue, int lowerBound, int upperBound){
        if (queue.isEmpty()){
            return null;
        }
        int val = Integer.parseInt(queue.peek());
        //利用BST的性质，如果值小于该子树的下边界或者大于其上边界，说明该子树已经走到尽头，该节点应该在另一棵子树上
        if (val < lowerBound || val > upperBound){
            return null;
        }else {
            queue.poll();
            //需与序列化用同样的遍历方式
            TreeNode root = new TreeNode(val);
            root.left = decode(queue, lowerBound, val);
            root.right = decode(queue, val, upperBound);
            return root;
        }
    }
}
