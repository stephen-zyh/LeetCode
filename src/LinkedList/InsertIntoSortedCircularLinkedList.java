package LinkedList;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 708
 * @date 2022/7/23 18:47
 */

class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
}

public class InsertIntoSortedCircularLinkedList {
    public Node insert(Node head, int insertVal) {
        if (head == null){
            head = new Node(insertVal);
            head.next = head;
            return head;
        }
        Node pre = head, cur = head.next;
        do {
            //比pre大，比cur小，插入到二者之间
            if (pre.val <= insertVal && cur.val >= insertVal){
                pre.next = new Node(insertVal, cur);
                return head;
            }else if (pre.val > cur.val){
                //遍历到升序排列元素的最后，该元素要么比最大值大，要么比最小值小
                if (pre.val <= insertVal || cur.val >= insertVal){
                    pre.next = new Node(insertVal, cur);
                    return head;
                }
            }
            pre = pre.next;
            cur = cur.next;
        }while (pre != head);
        //遍历了一圈都未能插入，说明原循环链表中所有元素相等，因此随便插入到哪里都可以
        pre.next = new Node(insertVal, cur);
        return head;
    }
}
