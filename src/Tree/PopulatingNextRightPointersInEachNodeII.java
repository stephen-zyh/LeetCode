package Tree;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 117
 * @date 2022/8/17 9:10
 */
public class PopulatingNextRightPointersInEachNodeII {
    public Node connect(Node root) {
        if (root == null){
            return null;
        }
        //1.左右子节点都存在，左节点连右节点，右节点连next
        //2.左子节点不存在而右子节点存在，右节点连next
        //3.左子节点存在而右子节点不存在，左节点连next
        if (root.left != null && root.right != null){
            root.left.next = root.right;
            root.right.next = getNextNode(root.next);
        }else if (root.left == null && root.right != null){
            root.right.next = getNextNode(root.next);
        }else if (root.left != null){
            root.left.next = getNextNode(root.next);
        }
        //遍历左右子树
        //如果先递归左子树，会出现左边无法与右边相连的case:[2,1,3,0,7,9,1,2,null,1,0,null,null,8,8,null,null,null,null,7]
        connect(root.right);
        connect(root.left);
        return root;
    }

    private Node getNextNode(Node root){
        if (root == null){
            return null;
        }
        if (root.left != null){
            return root.left;
        }
        if (root.right != null){
            return root.right;
        }
        if (root.next != null){
            return getNextNode(root.next);
        }
        return null;
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
