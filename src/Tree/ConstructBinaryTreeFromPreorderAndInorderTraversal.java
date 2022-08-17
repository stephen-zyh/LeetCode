package Tree;

import java.util.HashMap;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 105
 * @date 2022/8/13 17:56
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    HashMap<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //将inorder中值与其索引映射，方便在inorder中找到根节点
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return treeBuilder(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }
    //入参是preorder中pLeft <= i < pRight的子数组和inorder中iLeft <= i < iRight的子数组
    private TreeNode treeBuilder(int[] preorder, int pLeft, int pRight, int[] inorder, int iLeft, int iRight){
        //base case
        if (pLeft >= pRight || iLeft >= iRight){
            return null;
        }
        //先序遍历中第一个节点是根节点，找到其在inorder中的位置
        int index = map.get(preorder[pLeft]);
        int k = index - iLeft;  //k是inorder[index]左侧元素的个数
        TreeNode root = new TreeNode(inorder[index]);
        //root.left在传入的inorder子数组中的左侧找，root.right在其右侧找
        root.left = treeBuilder(preorder, pLeft + 1, pLeft + 1 + k, inorder, iLeft, index);
        root.right = treeBuilder(preorder, pLeft + 1 + k, pRight, inorder, index + 1, iRight);
        return root;
    }
}
