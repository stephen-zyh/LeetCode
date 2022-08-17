package LinkedList;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 237
 * @date 2022/7/17 20:08
 */
public class DeleteNode {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static void main(String[] args) {
        ListNode p4 = new ListNode(9, null);
        ListNode p3 = new ListNode(1, p4);
        ListNode p2 = new ListNode(5, p3);
        ListNode p1 = new ListNode(4, p2);
        new DeleteNode().deleteNode(p3);
        ListNode result = p1;
        while (result != null){
            System.out.print(result.val + "\t");
            result = result.next;
        }
    }
}
