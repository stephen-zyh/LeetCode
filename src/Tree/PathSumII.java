package Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 113，回溯
 * @date 2022/8/12 17:46
 */
public class PathSumII {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        findPath(root, targetSum);
        return result;
    }
    private void findPath(TreeNode root, int targetSum){
        if (root == null){
            return;
        }

        list.add(root.val); //路径添加
        //找到root.val == targetSum且当前节点已经是叶子节点，已经找到一条路径，将该路径添加到result中
        if (root.left == null && root.right == null && targetSum == root.val){
            result.add(new ArrayList<>(list));  //result.add(list);添加的是list对象，而不是当前list中的值
        }

        findPath(root.left, targetSum - root.val);
        findPath(root.right, targetSum - root.val);

        list.remove(list.size() - 1);   //路径回溯
    }

    public static void main(String[] args) {
        TreeNode n10 = new TreeNode(1);
        TreeNode n9 = new TreeNode(5);
        TreeNode n8 = new TreeNode(2);
        TreeNode n7 = new TreeNode(7);
        TreeNode n6 = new TreeNode(4, n9, n10);
        TreeNode n5 = new TreeNode(13);
        TreeNode n4 = new TreeNode(11, n7, n8);
        TreeNode n3 = new TreeNode(8, n5, n6);
        TreeNode n2 = new TreeNode(4, n4, null);
        TreeNode n1 = new TreeNode(5, n2, n3);

        List<List<Integer>> list = new PathSumII().pathSum(n1, 22);
        System.out.println(list);
    }
}
