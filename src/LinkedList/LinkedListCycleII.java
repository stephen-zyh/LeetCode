package LinkedList;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 142, 使用快慢双指针，参考leetcode 141实现
 * @date 2022/7/20 14:39
 */
public class LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        //当前链表为空，不可能有环
        if (head == null){
            return head;
        }
        ListNode slow = head, fast = head;
        boolean hasCycle = false;
        //快指针速度是慢指针速度的两倍，若有环，则fast与slow会相遇，否则fast到链表尾部，终止循环
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                hasCycle = true;
                break;
            }
        }
        //设slow走的总路程为n，在环里走的路程为x，fast走的总路程为2n，则slow再走n-x即可到环的首节点。
        //而头节点与环的首节点之间的路程也为n-x
        if (hasCycle){
            ListNode result = head;
            while (result != slow){
                slow = slow.next;
                result = result.next;
            }
            return result;
        }
        //没有环，返回null
        return null;
    }

    public static void main(String[] args) {
        ListNode p4 = new ListNode(-4);
        ListNode p3 = new ListNode(0, p4);
        ListNode p2 = new ListNode(2, p3);
        ListNode p1 = new ListNode(3, p2);
        p4.next = p2;

        ListNode cycle = new LinkedListCycleII().detectCycle(p1);
        System.out.println(cycle == p2);
    }
}
