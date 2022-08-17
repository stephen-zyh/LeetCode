package Tree;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 98
 * @date 2022/8/14 17:54
 */
public class ValidateBinarySearchTree {
    long lastVal = Long.MIN_VALUE;  //测试用例中节点的最小值为-2^31，是int的最小值，因此需要一个比-2^31还小的值作为初值
    public boolean isValidBST(TreeNode root) {
        //二叉搜索树的遍历和中序遍历相似
        //base case
        if (root == null){
            return true;
        }
        if (!isValidBST(root.left)){
            return false;
        }
        if (root.val <= lastVal){
            return false;
        }
        //更新lastVal，使其始终是已遍历节点中的最大值
        lastVal = root.val;
        return isValidBST(root.right);
    }
}
