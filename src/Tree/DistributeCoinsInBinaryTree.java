package Tree;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 979
 * @date 2022/8/16 21:09
 */
public class DistributeCoinsInBinaryTree {
    int result = 0;
    public int distributeCoins(TreeNode root) {
        moveCoins(root);
        return result;
    }
    //该方法返回某个节点缺少/多余的硬币的个数
    private int moveCoins(TreeNode root){
        if (root == null){
            return 0;
        }
        //获取左右节点缺少/多余的硬币的个数
        int left = moveCoins(root.left);
        int right = moveCoins(root.right);
        //移动次数应该为正，因此左右两侧都为绝对值
        result += Math.abs(left) + Math.abs(right);
        //该节点缺少/多余的硬币的个数
        return left + right + root.val - 1;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(3, new TreeNode(0), new TreeNode(0));
        int result = new DistributeCoinsInBinaryTree().distributeCoins(n1);
        System.out.println(result);
    }
}
