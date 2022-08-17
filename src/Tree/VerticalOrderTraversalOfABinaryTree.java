package Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 987
 * @date 2022/8/15 17:06
 */
public class VerticalOrderTraversalOfABinaryTree {
    HashMap<TreeNode, int[]> map = new HashMap<>();
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        map.put(root, new int[]{0, 0, root.val});
        preorderTraversal(root);
        ArrayList<int[]> list = new ArrayList<>(map.values());
        //自定义比较器，对list中元素进行排序
        list.sort((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            if (a[1] != b[1]) return a[1] - b[1];
            return a[2] - b[2];
        });
        List<List<Integer>> result = new ArrayList<>();
        //添加每一层元素
        for (int i = 0; i < list.size(); ) {
            int j = i;
            ArrayList<Integer> temp = new ArrayList<>();
            while (j < list.size() && list.get(i)[0] == list.get(j)[0]){
                temp.add(list.get(j++)[2]);
            }
            i = j;
            result.add(temp);
        }
        return result;
    }
    //使用前序遍历二叉树，建立节点和节点的纵坐标、横坐标、节点值的数组的映射
    private void preorderTraversal(TreeNode root){
        if (root == null){
            return;
        }
        int col = map.get(root)[0];
        int row = map.get(root)[1];
        if (root.left != null){
            map.put(root.left, new int[]{col - 1, row + 1, root.left.val});
            preorderTraversal(root.left);
        }
        if (root.right != null){
            map.put(root.right, new int[]{col + 1, row + 1, root.right.val});
            preorderTraversal(root.right);
        }
    }

    public static void main(String[] args) {
        TreeNode n7 = new TreeNode(7);
        TreeNode n6 = new TreeNode(6);
        TreeNode n5 = new TreeNode(5);
        TreeNode n4 = new TreeNode(4);
        TreeNode n3 = new TreeNode(3, n6, n7);
        TreeNode n2 = new TreeNode(2, n4, n5);
        TreeNode n1 = new TreeNode(1, n2, n3);

        List<List<Integer>> list = new VerticalOrderTraversalOfABinaryTree().verticalTraversal(n1);
        System.out.println(list);
    }
}
