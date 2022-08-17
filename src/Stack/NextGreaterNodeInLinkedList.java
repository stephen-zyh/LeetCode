package Stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1019
 * @date 2022/7/28 12:45
 */
public class NextGreaterNodeInLinkedList {
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public int[] nextLargerNodes(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        //将链表中的元素转存到列表中（因为要获取链表长度，即结果数组长度，此步骤必须）
        while (head != null){
            list.add(head.val);
            head = head.next;
        }
        int[] result = new int[list.size()];
        //与leetcode 739类似，栈不空且下一个元素更大时可出栈（栈中存放的是索引值），出栈完后当前元素入栈
        for (int i = 0; i < list.size(); i++) {
            int cur = list.get(i);
            while (!stack.isEmpty() && cur > list.get(stack.peek())){
                result[stack.pop()] = cur;
            }
            stack.push(i);
        }
        return result;
    }

    public static void main(String[] args) {
        ListNode p5 = new ListNode(5, null);
        ListNode p4 = new ListNode(3, p5);
        ListNode p3 = new ListNode(4, p4);
        ListNode p2 = new ListNode(7, p3);
        ListNode p1 = new ListNode(2, p2);

        int[] result = new NextGreaterNodeInLinkedList().nextLargerNodes(p1);
        System.out.println(Arrays.toString(result));
    }
}

