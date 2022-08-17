package Tree;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 437
 * @date 2022/8/12 20:34
 */
public class PathSumIII {
    int result = 0;
    //入参改为long是因为
    // [1000000000,1000000000,null,294967296,null,1000000000,null,1000000000,null,1000000000], 0
    public int pathSum(TreeNode root, long targetSum) {
        if (root == null){
            return 0;
        }
        //首先先序递归遍历每个节点，再以每个节点作为起始点递归寻找满足条件的路径。
        findPath(root, targetSum);
        pathSum(root.left, targetSum);
        pathSum(root.right, targetSum);
        return result;
    }

    //以root为根节点寻找满足条件的路径数目
    private void findPath(TreeNode root, long targetSum){
        if (root == null){
            return;
        }
        if (root.val == targetSum){
            result++;
        }
        findPath(root.left, targetSum - root.val);
        findPath(root.right, targetSum - root.val);
    }

    public static void main(String[] args) {
        TreeNode n9 = new TreeNode(1);
        TreeNode n8 = new TreeNode(-2);
        TreeNode n7 = new TreeNode(3);
        TreeNode n6 = new TreeNode(11);
        TreeNode n5 = new TreeNode(2, null, n9);
        TreeNode n4 = new TreeNode(3, n7, n8);
        TreeNode n3 = new TreeNode(-3, null, n6);
        TreeNode n2 = new TreeNode(5, n4, n5);
        TreeNode n1 = new TreeNode(10, n2, n3);

        int result = new PathSumIII().pathSum(n1, 8);
        System.out.println(result);
    }
}
