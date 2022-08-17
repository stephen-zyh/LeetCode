package Tree;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 337
 * @date 2022/8/17 19:00
 */
public class HouseRobberIII {
    public int rob(TreeNode root) {
        int[] result = postorderTraversal(root);
        return Math.max(result[0], result[1]);
    }
    //该方法返回一个length为2的数组result，result[0]表示不抢劫该节点时的最大值，result[1]是抢劫该节点时的最大值
    private int[] postorderTraversal(TreeNode root){
        //base case
        if (root == null){
            return new int[]{0,0};
        }
        //后序遍历
        int[] left = postorderTraversal(root.left);
        int[] right = postorderTraversal(root.right);

        int[] result = new int[2];
        //不抢劫该节点时，最大收益为左节点获得的最大收益和右节点获得的最大收益的总和
        //抢劫该节点时，最大收益为不抢劫左右节点的收益加上本节点的收益
        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        result[1] = left[0] + right[0] + root.val;

        return result;
    }
 }
