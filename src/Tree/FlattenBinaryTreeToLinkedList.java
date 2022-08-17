package Tree;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 114
 * @date 2022/8/16 10:13
 */
public class FlattenBinaryTreeToLinkedList {
    TreeNode last = null;   //记录上一个遍历的元素
    //按照右 -> 左 -> 中的方式进行遍历
    public void flatten(TreeNode root) {
        if (root == null) return;
        flatten(root.right);
        flatten(root.left);
        root.right = last;
        root.left = null;
        last = root;
    }

    public static void main(String[] args) {
        TreeNode n6 = new TreeNode(6);
        TreeNode n5 = new TreeNode(4);
        TreeNode n4 = new TreeNode(3);
        TreeNode n3 = new TreeNode(5, null, n6);
        TreeNode n2 = new TreeNode(2, n4, n5);
        TreeNode n1 = new TreeNode(1, n2, n3);

        new FlattenBinaryTreeToLinkedList().flatten(n1);
    }
}
