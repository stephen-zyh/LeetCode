package Tree;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1008
 * @date 2022/8/14 9:08
 */
public class ConstructBinarySearchTreeFromPreorderTraversal {
    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = null;
        for (int i = 0; i < preorder.length; i++) {
            root = addNode(root, preorder[i]);
        }
        return root;
    }
    private TreeNode addNode(TreeNode root, int value){
        //base case
        if (root == null){
            return new TreeNode(value);
        }
        //小于当前节点值，遍历左子树，否则遍历右子树
        if (value < root.val){
            root.left = addNode(root.left, value);
        }else {
            root.right = addNode(root.right, value);
        }
        return root;
    }
}
