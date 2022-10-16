package DynamicProgramming1D;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 95，leetcode 96的相似题目，虽然题目标签有动态规划，但做起来不如递归方便
 * @date 2022/10/14 10:42
 */
public class UniqueBinarySearchTreesII {
    public List<TreeNode> generateTrees(int n) {
        return findUniqueBST(1, n);
    }
    private List<TreeNode> findUniqueBST(int start, int end){
        List<TreeNode> result = new ArrayList<>();
        //如果起始节点值大于结束节点，说明没有待生成的子树了，将null添加到结果中
        if (start > end){
            result.add(null);
            return result;
        }
        //否则以[start, i - 1]构造左子树，i为根节点，[i + 1, end]构造右子树
        for (int i = start; i <= end; i++) {
            //方法返回的是以i两侧各个数为根节点的不同构造的BST，因此根节点i要和不同的左子树、右子树结合生成新的树
            List<TreeNode> leftPart = findUniqueBST(start, i - 1);
            List<TreeNode> rightPart = findUniqueBST(i + 1, end);
            for (TreeNode left : leftPart){
                for (TreeNode right : rightPart){
                    result.add(new TreeNode(i, left, right));
                }
            }
        }
        return result;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
 }
