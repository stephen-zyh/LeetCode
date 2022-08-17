package Tree;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 543
 * @date 2022/8/11 20:07
 */
public class DiameterOfBinaryTree {
    public int diameterOfBinaryTree(TreeNode root) {
        //base case
        if (root == null){
            return 0;
        }
        //该节点的diameter为左右深度之和
        int distance = maxTreeDepth(root.left) + maxTreeDepth(root.right);
        //递归调用，对于每一次调用，最大的diameter应为该节点及其左、右节点的diameter三者中的最大值
        return Math.max(distance, Math.max(diameterOfBinaryTree(root.left),diameterOfBinaryTree(root.right)));
    }
    private int maxTreeDepth(TreeNode root){
        //base case
        if (root == null){
            return 0;
        }
        //最大深度为左右子节点深度大者加1
        int leftDepth = maxTreeDepth(root.left);
        int rightDepth = maxTreeDepth(root.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n3 = new TreeNode(3);
        TreeNode n2 = new TreeNode(2, n4, n5);
        TreeNode n1 = new TreeNode(1, n2, n3);

        int result = new DiameterOfBinaryTree().diameterOfBinaryTree(n1);
        System.out.println(result);
    }
}
