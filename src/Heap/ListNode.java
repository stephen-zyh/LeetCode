package Heap;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 23中ListNode的定义
 * @date 2022/8/29 22:32
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
