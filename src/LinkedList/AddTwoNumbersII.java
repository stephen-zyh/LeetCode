package LinkedList;

import java.util.Stack;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 445，逆序处理使用栈
 * @date 2022/7/23 16:43
 */
public class AddTwoNumbersII {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        //将l1和l2中储存的值分别压入栈中
        while (l1 != null){
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null){
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        int sum;
        ListNode head = null;
        //只要任一链表还有高位或者计算结果还有进位，就需要继续运算
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0){
            sum = carry;
            if (stack1.isEmpty()){
                sum += 0;
            }else {
                sum += stack1.pop();
            }

            if (stack2.isEmpty()){
                sum += 0;
            }else {
                sum += stack2.pop();
            }

            ListNode node = new ListNode(sum % 10);
            //计算结果插入到链表头部
            node.next = head;
            head = node;
            carry = sum / 10;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode p4 = new ListNode(3, null);
        ListNode p3 = new ListNode(4, p4);
        ListNode p2 = new ListNode(2, p3);
        ListNode p1 = new ListNode(7, p2);

        ListNode q3 = new ListNode(4, null);
        ListNode q2 = new ListNode(6, q3);
        ListNode q1 = new ListNode(5, q2);

        ListNode head = new AddTwoNumbersII().addTwoNumbers(p1, q1);

        while (head != null){
            System.out.print(head.val + "\t");
            head = head.next;
        }
    }
}
