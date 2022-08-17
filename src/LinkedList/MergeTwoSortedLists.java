package LinkedList;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 21
 * @date 2022/7/17 15:59
 */
public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null){
            return list2;
        }
        if (list2 == null){
            return list1;
        }
        ListNode result = new ListNode();
        if (list1.val < list2.val){
            result.val = list1.val;
            list1 = list1.next;
        }else {
            result.val = list2.val;
            list2 = list2.next;
        }
        result.next = mergeTwoLists(list1, list2);
        return result;
    }

    public static void main(String[] args) {
        ListNode p2 = new ListNode(4, null);
        ListNode p1 = new ListNode(2, p2);
        ListNode list1 = new ListNode(1, p1);

        ListNode q2 = new ListNode(4, null);
        ListNode q1 = new ListNode(3, q2);
        ListNode list2 = new ListNode(1, q1);

        ListNode result = new MergeTwoSortedLists().mergeTwoLists(list1, list2);
        while (result != null){
            System.out.print(result.val + "\t");
            result = result.next;
        }
    }
}