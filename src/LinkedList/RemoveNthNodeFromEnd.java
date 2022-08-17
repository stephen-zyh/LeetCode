package LinkedList;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 19，使用快慢双指针
 * @date 2022/7/23 17:22
 */
public class RemoveNthNodeFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = null, dummy = new ListNode(0);
        dummy.next = head;
        //两个指针都从dummy而不是head开始，以便能够遍历整个链表
        ListNode slow = dummy, fast = dummy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        //快指针先移动n步，快慢指针再一起移动，快指针移动到链表尾部时慢指针指向待移除元素
        while (fast != null){
            fast = fast.next;
            //pre记录slow前一个节点，保证移除slow处元素后剩余元素仍然能够连续
            pre = slow;
            slow = slow.next;
        }
        //链表中不只有一个元素时返回dummy.next，否则返回null
        if (pre != null){
            pre.next = slow.next;
            return dummy.next;
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode p5 = new ListNode(5, null);
        ListNode p4 = new ListNode(4, p5);
        ListNode p3 = new ListNode(3, p4);
        ListNode p2 = new ListNode(2, p3);
        ListNode p1 = new ListNode(1, p2);
        ListNode head = new RemoveNthNodeFromEnd().removeNthFromEnd(p1, 2);
        while (head != null){
            System.out.print(head.val + "\t");
            head = head.next;
        }
    }
}
