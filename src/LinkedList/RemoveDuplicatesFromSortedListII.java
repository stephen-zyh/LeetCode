package LinkedList;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 82，使用快慢双指针
 * @date 2022/7/19 17:13
 */
public class RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        //如果链表为空或者链表中只有一个元素，不可能有重复，直接返回
        if (head == null || head.next == null){
            return head;
        }
        //将链表中没有重复的元素依次连接到dummy后
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy, fast = head, slow = head;
        while (fast != null){
            if (slow.val == fast.val){
                //如果slow和fast值相同，则让fast向后移动
                fast = fast.next;
            }else if (slow.next == fast){
                //如果与slow值不同的fast就在slow下一个，说明slow所在的值只出现过一次，将其添加到结果中
                pre.next = slow;
                pre = pre.next;
                slow = fast;
            }else {
                //跳过重复元素，判断下一个不同值得元素
                slow = fast;
            }
        }
        //如果链表最后一个元素只出现了一次，也应该将其加到结果中
        if (slow.next == null){
            pre.next = slow;
            pre = pre.next;
        }
        //原链表最后是一些相同值的元素，应断开其与最后一个不重复元素之间的连接
        pre.next = null;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode p7 = new ListNode(5, null);
        ListNode p6 = new ListNode(4, p7);
        ListNode p5 = new ListNode(4, p6);
        ListNode p4 = new ListNode(3, p5);
        ListNode p3 = new ListNode(3, p4);
        ListNode p2 = new ListNode(2, p3);
        ListNode p1 = new ListNode(1, p2);

        ListNode head = new RemoveDuplicatesFromSortedListII().deleteDuplicates(p1);

        while (head != null){
            System.out.print(head.val + "\t");
            head = head.next;
        }
    }
}
