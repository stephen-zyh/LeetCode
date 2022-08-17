package Tree;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 226
 * @date 2022/8/11 21:11
 */
public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        //base case
        if (root == null){
            return null;
        }
        //递归调用，对于每一次调用，用一个临时变量以方便交换left和right子树，对于子树做同样操作
        TreeNode tempNode = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = tempNode;
        return root;
    }
}
