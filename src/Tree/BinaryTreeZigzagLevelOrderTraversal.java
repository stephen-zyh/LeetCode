package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 103
 * @date 2022/8/15 11:52
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        //树中节点为空，返回空列表
        if (root == null){
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true; //决定从左到右添加还是相反，遍历完每一层后修改leftToRight的值
        //BFS，记录本层节点个数，将本层节点依次出队的同时添加节点的左右子节点，对每一层重复上述操作，直至队列为空（遍历完所有节点）
        while (!queue.isEmpty()){
            ArrayList<Integer> list = new ArrayList<>();
            int count = queue.size();
            while (count > 0){
                TreeNode currNode = queue.poll();
                if (leftToRight){
                    list.add(currNode.val);
                }else {
                    list.add(0, currNode.val);
                }
                if (currNode.left != null){
                    queue.offer(currNode.left);
                }
                if (currNode.right != null){
                    queue.offer(currNode.right);
                }
                count--;
            }
            //每一层遍历完后将结果添加到result中
            result.add(list);
            leftToRight = !leftToRight;
        }
        return result;
    }
}
