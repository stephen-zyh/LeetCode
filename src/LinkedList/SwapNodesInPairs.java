package LinkedList;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 24
 * @date 2022/7/18 12:26
 */
public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = pre.next;
        //与leetcode 92反转后cur的位置不同，两个一反转需要每次反转之后后移cur和pre
        while (cur != null && cur.next != null){
            //利用头插法将后续元素移到前面
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;

            pre = cur;
            cur = cur.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode p4 = new ListNode(4, null);
        ListNode p3 = new ListNode(3, p4);
        ListNode p2 = new ListNode(2, p3);
        ListNode p1 = new ListNode(1, p2);

        ListNode head = new SwapNodesInPairs().swapPairs(p1);
//        ListNode head = new SwapNodesInPairs().swapPairs(null);
//        ListNode head = new SwapNodesInPairs().swapPairs(new ListNode(1, null));
        while (head != null){
            System.out.print(head.val + "\t");
            head = head.next;
        }
    }
}
