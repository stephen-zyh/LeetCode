package LinkedList;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 2
 * @date 2022/7/20 15:13
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //其中一个为空，直接返回另一个（题目给定链表长度最短为1，因此这两个if实际上可以去除）
        if (l1 == null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }
        ListNode head = l1;
        ListNode pre = null;
        int carry = 0;
        //两者都不为null的部分，将结果加到l1中，并保存进位
        while (l1 != null && l2 != null){
            l1.val = l1.val + l2.val + carry;
            if (l1.val >= 10){
                l1.val %= 10;
                carry = 1;
            }else {
                carry = 0;
            }
            //因为循环结束后l1可能为null，必须有pre保存l1前一个节点的信息
            pre = l1;
            l1 = l1.next;
            l2 = l2.next;
        }
        //l1与l2一样长，处理最后的进位即可
        if (l1 == null && l2 == null){
            if (carry == 1){
                pre.next = new ListNode(1, null);
                return head;
            }
        } else if(l1 == null) {
            //l2更长，让l1的末尾指向l2中当前节点
            pre.next = l2;
            l1 = pre.next;
        }
        //余下的节点依次与carry相加运算即可
        while (l1 != null){
            l1.val += carry;
            if (l1.val >= 10){
                l1.val %= 10;
                carry = 1;
            }else {
                carry = 0;
            }
            pre = l1;
            l1 = l1.next;
        }
        //处理最后的进位位
        if (carry == 1){
            pre.next = new ListNode(1, null);
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode p7 = new ListNode(9, null);
        ListNode p6 = new ListNode(9, p7);
        ListNode p5 = new ListNode(9, p6);
        ListNode p4 = new ListNode(9, p5);
        ListNode p3 = new ListNode(9, p4);
        ListNode p2 = new ListNode(9, p3);
        ListNode p1 = new ListNode(9, p2);

        ListNode q4 = new ListNode(9, null);
        ListNode q3 = new ListNode(9, q4);
        ListNode q2 = new ListNode(9, q3);
        ListNode q1 = new ListNode(9, q2);

        ListNode head = new AddTwoNumbers().addTwoNumbers(p1, q1);

        while (head != null){
            System.out.print(head.val + "\t");
            head = head.next;
        }
    }
}
