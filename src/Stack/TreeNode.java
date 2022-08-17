package Stack;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: 二叉树节点的定义
 * @date 2022/7/29 20:57
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
