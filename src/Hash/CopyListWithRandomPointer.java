package Hash;

import java.util.HashMap;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 138
 * @date 2022/8/9 8:55
 */
public class CopyListWithRandomPointer {
    public Node copyRandomList(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node newNode = head;
        //第一次遍历复制节点，先将节点数据保存到新的节点clone中，并存储节点和clone的映射
        while (head != null){
            Node clone = new Node(head.val);
            map.put(head, clone);
            head = head.next;
        }
        head = newNode;
        //第二次遍历复制节点关系
        while (head != null){
            map.get(head).next = map.get(head.next);
            map.get(head).random = map.get(head.random);
            head = head.next;
        }
        //head映射到的是深拷贝的链表的头指针
        return map.get(head);
    }
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}

