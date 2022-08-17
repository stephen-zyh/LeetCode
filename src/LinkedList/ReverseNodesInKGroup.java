package LinkedList;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 25
 * @date 2022/7/18 14:59
 */
public class ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = pre.next;
        while (cur != null){
            ListNode test = cur;
            int i;
            //查看是否还有k个节点，如果不足k个节点则不反转
            for (i = 0; i < k - 1 && test.next != null; i++) {
                test = test.next;
            }
            //与leetcode 24两个一反转类似，但是此处反转k-1次（即反转k个元素）后才后移一次pre和cur
            if (i == k - 1){
                for (int j = 0; j < k - 1; j++) {
                    ListNode next = cur.next;
                    cur.next = next.next;
                    next.next = pre.next;
                    pre.next = next;
                }
            }else{
                //后续不足k个节点，不用反转，也不用关心pre和cur，直接退出循环即可
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode p5 = new ListNode(5, null);
        ListNode p4 = new ListNode(4, p5);
        ListNode p3 = new ListNode(3, p4);
        ListNode p2 = new ListNode(2, p3);
        ListNode p1 = new ListNode(1, p2);

        ListNode head = new ReverseNodesInKGroup().reverseKGroup(p1, 3);
        while (head != null){
            System.out.print(head.val + "\t");
            head = head.next;
        }
    }
}
