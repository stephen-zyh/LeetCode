package Tree;

import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: N叉树的定义
 * @date 2022/8/12 9:26
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
