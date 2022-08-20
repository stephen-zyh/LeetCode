package Tree;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 450
 * @date 2022/8/18 9:37
 */
public class DeleteNodeInBST {
    public TreeNode deleteNode(TreeNode root, int key) {
        //base case
        if (root == null){
            return null;
        }
        //小于走左边，大于走右边
        //等于key的值时：
        //1.若key的左子树为空，用右子树的root节点代替key所在节点
        //2.若key的右子树为空，用左子树的root节点代替key所在节点
        //3.若左右子树均不为空，将值为key的节点的值替换为其右子树中的最小值，再删除右子树中值最小的节点
        if (root.val > key){
            root.left = deleteNode(root.left, key);
        }else if (root.val < key){
            root.right = deleteNode(root.right, key);
        }else {
            if (root.left == null){
                return root.right;
            }
            if (root.right == null){
                return root.left;
            }
            //找到右子树中值最小的节点minVal，root的值由key替换为minVal，再删除minVal所在节点
            TreeNode minNode = minValueNode(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root.right, minNode.val);
        }
        return root;
    }

    private TreeNode minValueNode(TreeNode root){
        while (root.left != null){
            root = root.left;
        }
        return root;
    }
}
