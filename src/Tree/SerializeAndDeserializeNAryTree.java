package Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 428
 * @date 2022/8/20 15:42
 */
public class SerializeAndDeserializeNAryTree {
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        ArrayList<String> list = new ArrayList<>();
        encode(root, list);
        return String.join(",", list);
    }

    private void encode(Node root, ArrayList<String> list){
        if (root == null){
            return;
        }
        //先序遍历，先节点后孩子，用list记录每个节点的值及其孩子的个数
        list.add(String.valueOf(root.val));
        list.add(String.valueOf(root.children.size()));
        for (Node child : root.children){
            encode(child, list);
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.isEmpty()){
            return null;
        }
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return decode(queue);
    }

    private Node decode(Queue<String> queue){
        if (queue.isEmpty()){
            return null;
        }
        int val = Integer.parseInt(queue.poll());
        int size = Integer.parseInt(queue.poll());
        //与序列化一样采用先序遍历
        Node currNode = new Node(val, new ArrayList<>());
        for (int i = 0; i < size; i++) {
            currNode.children.add(decode(queue));
        }

        return currNode;
    }
}
