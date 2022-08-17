package LinkedList;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 83，使用快慢双指针
 * @date 2022/7/19 16:41
 */
public class RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        //如果链表为空或者链表中只有一个元素，不可能有重复，直接返回
        if (head == null || head.next == null){
            return head;
        }
        ListNode slow = head, fast = head.next;
        while (fast != null){
            if (fast.val != slow.val){
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        //最后一个元素值可能出现多次，记录了第一个之后，应断开与后续相同元素值的连接
        slow.next = null;
        return head;
    }

    public static void main(String[] args) {
        ListNode p5 = new ListNode(3, null);
        ListNode p4 = new ListNode(3, p5);
        ListNode p3 = new ListNode(2, p4);
        ListNode p2 = new ListNode(1, p3);
        ListNode p1 = new ListNode(1, p2);

        ListNode head = new RemoveDuplicatesFromSortedList().deleteDuplicates(p1);

        while (head != null){
            System.out.print(head.val + "\t");
            head = head.next;
        }
    }
}
