package Tree;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 104
 * @date 2022/8/11 21:26
 */
public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        //base case
        if (root == null){
            return 0;
        }
        ////最大深度为左右子节点深度大者加1
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        TreeNode n4 = new TreeNode(15);
        TreeNode n5 = new TreeNode(7);
        TreeNode n3 = new TreeNode(20, n4, n5);
        TreeNode n2 = new TreeNode(9);
        TreeNode n1 = new TreeNode(3, n2, n3);

        int result = new MaximumDepthOfBinaryTree().maxDepth(n1);
        System.out.println(result);
    }
}
