package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 297
 * @date 2022/8/19 9:18
 */
public class SerializeAndDeserializeBinaryTree {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        //BFS
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<String> list = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode currNode = queue.poll();
                if (currNode != null){
                    queue.offer(currNode.left);
                    queue.offer(currNode.right);
                    list.add(String.valueOf(currNode.val));
                }else {
                    //用"#"代表null
                    list.add("#");
                }
            }
        }
        return String.join(" ", list);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodesInfo = data.split(" ");   //提取节点信息
        Queue<TreeNode> queue = new LinkedList<>();
        int index = 0;
        //树为空的情况
        if (nodesInfo[0].equals("#")){
            return null;
        }else {
            //BFS
            TreeNode root = new TreeNode(Integer.parseInt(nodesInfo[index++]));
            queue.offer(root);
            while (!queue.isEmpty()){
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode currNode = queue.poll();
                    if (currNode != null){
                        String left = nodesInfo[index++];
                        String right = nodesInfo[index++];

                        currNode.left = decode(left);
                        currNode.right = decode(right);

                        queue.offer(currNode.left);
                        queue.offer(currNode.right);
                    }
                }
            }
            return root;
        }
    }

    private TreeNode decode(String s){
        if (s.equals("#")){
            return null;
        }else {
            return new TreeNode(Integer.parseInt(s));
        }
    }
}
