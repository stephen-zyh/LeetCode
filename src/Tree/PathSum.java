package Tree;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 112
 * @date 2022/8/12 11:44
 */
public class PathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null){
            return false;
        }else if (root.val == targetSum){
            //找到root.val == targetSum且当前节点已经是叶子节点，已经找到一条路径，返回true
            if (root.left == null && root.right == null){
                return true;
            }
            //找到root.val == targetSum且当前节点已经是叶子节点，判断子节点是否路径和为0
            return hasPathSum(root.left, 0) || hasPathSum(root.right, 0);
        }else {
            //否则继续遍历子节点
            return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
        }
    }

    public static void main(String[] args) {
        TreeNode n8 = new TreeNode(-1);
        TreeNode n4 = new TreeNode(1, n8, null);
        TreeNode n5 = new TreeNode(3);
        TreeNode n6 = new TreeNode(-2);
        TreeNode n2 = new TreeNode(-2, n4, n5);
        TreeNode n3 = new TreeNode(-3, n6, null);
        TreeNode n1 = new TreeNode(1, n2, n3);

        boolean hasPathSum = new PathSum().hasPathSum(n1, -1);
        System.out.println(hasPathSum);
    }
}
