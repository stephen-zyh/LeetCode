package Tree;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 108
 * @date 2022/8/12 11:05
 */
public class ConvertSortedArrayToBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        return treeBuilder(nums, 0, nums.length);
    }
    //treeBuilder()方法的参数实际上是数组nums从start到end的切片，即一个子数组
    private TreeNode treeBuilder(int[] nums, int start, int end){
        //base case: 子数组中不包含任何元素
        if (start == end){
            return null;
        }
        int middle = (start + end) / 2;
        TreeNode root = new TreeNode(nums[middle]);
        //将子数组从middle分为前后两个部分，分别给左右节点
        root.left = treeBuilder(nums, start, middle);
        root.right = treeBuilder(nums, middle + 1, end);
        return root;
    }
}
