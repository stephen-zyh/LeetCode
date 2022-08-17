package Tree;

import java.util.HashMap;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 889
 * @date 2022/8/14 16:13
 */
public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {
    HashMap<Integer, Integer> map = new HashMap<>();
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        for (int i = 0; i < postorder.length; i++) {
            map.put(postorder[i], i);
        }
        return treeBuilder(preorder, 0, preorder.length, postorder, 0, postorder.length);
    }
    //参数实际是preorder中preLeft <= i < preRight的子数组，和postorder中postLeft <= i < postRight的子数组
    private TreeNode treeBuilder(int[] preorder, int preLeft, int preRight, int[] postorder, int postLeft, int postRight){
        //base case
        if (preLeft >= preRight || postLeft >= postRight){
            return null;
        }
        TreeNode root = new TreeNode(preorder[preLeft]);
        //子数组中只有一个节点，作为根节点返回
        if (preLeft + 1 == preRight){
            return root;
        }
        int index = map.get(preorder[preLeft + 1]); //root.left.val的索引
        int k = index - postLeft + 1;   //k为左子树中节点的数目
        root.left = treeBuilder(preorder, preLeft + 1, preLeft + k + 1, postorder, postLeft, postLeft + k);
        root.right = treeBuilder(preorder, preLeft + k + 1, preRight, postorder, postLeft + k, postRight - 1);
        return root;
    }
}
