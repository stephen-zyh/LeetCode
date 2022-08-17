package LinkedList;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: 链表的定义，因为经常用到，所以此处单独拿出
 * @date 2022/7/17 16:34
 */
public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
