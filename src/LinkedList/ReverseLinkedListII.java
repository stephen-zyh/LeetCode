package LinkedList;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 92
 * @date 2022/7/18 8:34
 */
public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        //为方便处理链表，增加一个指向第一个节点的指针
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        //移动pre,使得pre.next（即cur）为链表中第left个元素
        for (int i = 1; i < left; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        //使用头插法，将每个[left,right]之间的next指向的元素都移动到紧挨pre指针之后的位置
        for (int i = 0; i < right - left; i++) {
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode p5 = new ListNode(5, null);
        ListNode p4 = new ListNode(4, p5);
        ListNode p3 = new ListNode(3, p4);
        ListNode p2 = new ListNode(2, p3);
        ListNode p1 = new ListNode(1, p2);
        ListNode head = new ReverseLinkedListII().reverseBetween(p1, 2, 4);
//        ListNode p2 = new ListNode(5, null);
//        ListNode p1 = new ListNode(3, p2);
//        ListNode head = new ReverseLinkedListII().reverseBetween(p1, 1, 2);
        while (head != null){
            System.out.print(head.val + "\t");
            head = head.next;
        }
    }
}
