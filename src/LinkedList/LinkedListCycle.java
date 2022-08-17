package LinkedList;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 141
 * @date 2022/7/20 11:47
 */
public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        //当前链表为空，不可能有环
        if (head == null){
            return false;
        }
        ListNode slow = head, fast = head;
        //快指针速度是慢指针速度的两倍，若有环，则fast与slow会相遇，否则fast到链表尾部，终止循环
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode p4 = new ListNode(-4);
        ListNode p3 = new ListNode(0, p4);
        ListNode p2 = new ListNode(2, p3);
        ListNode p1 = new ListNode(3, p2);
        p4.next = p2;

        System.out.println(new LinkedListCycle().hasCycle(p1));
    }
}
