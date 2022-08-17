package LinkedList;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 206
 * @date 2022/7/17 21:04
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        //当前链表为空或只有一个节点，直接返回
        if (head == null || head.next == null){
            return head;
        }
        //否则递归
        ListNode newhead = reverseList(head.next);
        //修改指针
        head.next.next = head;
        head.next = null;
        return newhead;
    }

    public static void main(String[] args) {
        ListNode p5 = new ListNode(5, null);
        ListNode p4 = new ListNode(4, p5);
        ListNode p3 = new ListNode(3, p4);
        ListNode p2 = new ListNode(2, p3);
        ListNode p1 = new ListNode(1, p2);
        new ReverseLinkedList().reverseList(p1);
        ListNode result = p5;
        while (result != null){
            System.out.print(result.val + "\t");
            result = result.next;
        }
    }
}
