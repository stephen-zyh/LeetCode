package Stack;

import java.util.Stack;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 173
 * @date 2022/7/29 16:22
 */
public class BSTIterator {
    TreeNode cur;
    Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        cur = root;
        stack = new Stack<TreeNode>();
    }

    public int next() {
        while (cur != null){
            stack.push(cur);
            cur = cur.left;
        }
        cur = stack.pop();
        int result = cur.val;
        cur = cur.right;
        return result;
    }

    public boolean hasNext() {
        return cur != null || !stack.isEmpty();
    }
}
