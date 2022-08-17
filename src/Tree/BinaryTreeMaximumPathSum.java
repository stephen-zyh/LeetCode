package Tree;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 124
 * @date 2022/8/13 10:03
 */
public class BinaryTreeMaximumPathSum {
    int result = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        getMax(root);
        return result;
    }

    private int getMax(TreeNode root){
        if (root == null){
            return 0;
        }
        //子路径的和为负则置零，相当于该条支路弃而不用
        int left = Math.max(0, getMax(root.left));
        int right = Math.max(0, getMax(root.right));

        //更新result的最大值
        result = Math.max(result, root.val + left + right);
        //返回的是单侧路径
        return root.val + Math.max(left, right);
    }

    public static void main(String[] args) {
        TreeNode n4 = new TreeNode(15);
        TreeNode n5 = new TreeNode(7);
        TreeNode n2 = new TreeNode(9);
        TreeNode n3 = new TreeNode(20, n4, n5);
        TreeNode n1 = new TreeNode(-10, n2, n3);

        int maxPathSum = new BinaryTreeMaximumPathSum().maxPathSum(n1);
        System.out.println(maxPathSum);
    }
}
