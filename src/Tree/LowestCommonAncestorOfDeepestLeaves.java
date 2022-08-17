package Tree;

import java.util.HashMap;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1123
 * @date 2022/8/17 11:20
 */
public class LowestCommonAncestorOfDeepestLeaves {
    HashMap<Integer, Integer> map = new HashMap<>();    //由于题目给出，节点值都是独一无二的，建立节点值到节点所在子树的深度的映射
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        //base case
        if (root == null){
            return null;
        }
        //递归，从下往上处理，采用后序遍历
        TreeNode leftNode = lcaDeepestLeaves(root.left);
        TreeNode rightNode = lcaDeepestLeaves(root.right);
        //1.如果是叶子节点，记录叶子节点深度并返回该叶子节点
        //2.左右两侧有一侧非空，节点深度为非空一侧深度加1，返回非空一侧返回的节点
        //3.1 左侧比右侧深，最深的叶子节点只可能在左侧出现，节点所在子树深度为左子树深度加1，并返回左子树返回的节点
        //3.2 右侧比左侧深，与上一情况同理
        //3.3 左右一样深，记录当前节点深度，并返回当前节点作为暂时的祖先节点
        if (root.left == null && root.right == null){
            map.put(root.val, 1);
            return root;
        }else if (root.left != null && root.right == null){
            map.put(root.val, map.get(root.left.val) + 1);
            return leftNode;
        }else if (root.left == null){
            map.put(root.val, map.get(root.right.val) + 1);
            return rightNode;
        }else {
            int leftDepth = map.get(root.left.val);
            int rightDepth = map.get(root.right.val);
            if (leftDepth > rightDepth){
                map.put(root.val, leftDepth + 1);
                return leftNode;
            }else if (leftDepth == rightDepth){
                map.put(root.val, leftDepth + 1);
                return root;
            }else {
                map.put(root.val, rightDepth + 1);
                return rightNode;
            }
        }
    }

    public static void main(String[] args) {
        TreeNode n9 = new TreeNode(4);
        TreeNode n8 = new TreeNode(7);
        TreeNode n7 = new TreeNode(8);
        TreeNode n6 = new TreeNode(0);
        TreeNode n5 = new TreeNode(2, n8, n9);
        TreeNode n4 = new TreeNode(6);
        TreeNode n3 = new TreeNode(1, n6, n7);
        TreeNode n2 = new TreeNode(5, n4, n5);
        TreeNode n1 = new TreeNode(3, n2, n3);

        TreeNode result = new LowestCommonAncestorOfDeepestLeaves().lcaDeepestLeaves(n1);
        System.out.println(result.val);
    }
}
