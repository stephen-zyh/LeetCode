package Tree;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 116
 * @date 2022/8/16 22:17
 */
public class PopulatingNextRightPointersInEachNode {
    public Node connect(Node root) {
        if (root == null){
            return null;
        }
        //左节点的next是右节点，右节点的next是其父节点的next的左节点
        if (root.left != null && root.right != null){
            root.left.next = root.right;
            if (root.next != null){
                root.right.next = root.next.left;
            }
        }
        //遍历左右子树
        connect(root.left);
        connect(root.right);
        return root;
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
