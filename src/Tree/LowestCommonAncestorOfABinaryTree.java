package Tree;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 236
 * @date 2022/8/15 10:24
 */
public class LowestCommonAncestorOfABinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //一棵子树遍历到尽头，或者该节点为p、q之一
        if (root == null || root == p || root == q){
            return root;
        }
        //本节点没找到，在其左右子树找
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //1.左右子树各有一个所需节点，返回本节点
        //2.只有左子树有所需节点，返回遍历左子树找到的节点（可能是p、q中的一个，也可能在左子树的某个子树中左右子树各有一个所需节点，那么返回的是该左子树的子树的根节点）
        //3.右子树与左子树同理
        if (left != null && right != null){
            return root;
        }else if (left != null){
            return left;
        }else if (right != null){
            return right;
        }
        //没有找到公共祖先
        return null;
    }
}
