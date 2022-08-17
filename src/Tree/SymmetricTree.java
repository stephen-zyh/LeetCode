package Tree;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 101
 * @date 2022/8/12 10:03
 */
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        return isMiorr(root, root);
    }
    private boolean isMiorr(TreeNode leftTree, TreeNode rightTree){
        if (leftTree == null && rightTree == null){
            return true;
        }else if (leftTree == null || rightTree == null){
            //左右子树不同时为空，树结构不对称，直接返回即可，不用比较值
            return false;
        }else if (leftTree.val != rightTree.val){
            //树结构对称但节点值不等
            return false;
        }else {
            //递归调用，对子树进行判断
            return isMiorr(leftTree.right, rightTree.left) && isMiorr(leftTree.left, rightTree.right);
        }
    }

    public static void main(String[] args) {
        TreeNode n4 = new TreeNode(3);
        TreeNode n5 = new TreeNode(4);
        TreeNode n6 = new TreeNode(4);
        TreeNode n7 = new TreeNode(3);
        TreeNode n2 = new TreeNode(2, n4, n5);
        TreeNode n3 = new TreeNode(2, n6, n7);
        TreeNode n1 = new TreeNode(1, n2, n3);

        boolean isSymmetric = new SymmetricTree().isSymmetric(n1);
        System.out.println(isSymmetric);

    }
}
