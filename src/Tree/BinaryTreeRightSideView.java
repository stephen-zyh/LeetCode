package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 199
 * @date 2022/8/15 16:32
 */
public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        //树中节点为空，返回空列表
        if (root == null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        //BFS，记录本层节点个数，将本层节点依次出队的同时添加节点的左右子节点，对每一层重复上述操作，直至队列为空（遍历完所有节点）
        //对于本题要求输出树的右视图，因此每一层只需要添加最后一个节点
        while (!queue.isEmpty()){
            int count = queue.size();
            while (count > 0){
                TreeNode currNode = queue.poll();
                if (currNode.left != null){
                    queue.offer(currNode.left);
                }
                if (currNode.right != null){
                    queue.offer(currNode.right);
                }
                if (count == 1){
                    result.add(currNode.val);
                }
                count--;
            }
        }
        return result;
    }
}
