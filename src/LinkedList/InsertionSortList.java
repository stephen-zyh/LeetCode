package LinkedList;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 147
 * @date 2022/7/24 11:58
 */
public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        //dummy始终指向链表头节点，pre是已排序部分的最后一个节点，cur是未排序的第一个节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode test, pre = head, cur = head.next;
        while (cur != null){
            //cur值比已排序的部分值都大，不用移动cur位置，将pre和cur后移即可
            if (cur.val >= pre.val){
                cur = cur.next;
                pre = pre.next;
                continue;
            }
            //cur值比已排序的部分值都小，插到链表头部，新的cur是移动前的cur的后继，pre不变
            if (cur.val <= dummy.next.val){
                pre.next = cur.next;
                cur.next = dummy.next;
                dummy.next = cur;
                cur = pre.next;
                continue;
            }
            //不是前面两种情况，插到已排序部分的中间，新的cur是移动前的cur的后继，pre不变
            for (test = dummy.next; test != pre; test = test.next){
                if (test.val <= cur.val && cur.val <= test.next.val){
                    pre.next = cur.next;
                    cur.next = test.next;
                    test.next = cur;
                    break;
                }
            }
            cur = pre.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode p4 = new ListNode(3, null);
        ListNode p3 = new ListNode(1, p4);
        ListNode p2 = new ListNode(2, p3);
        ListNode p1 = new ListNode(4, p2);

        ListNode head = new InsertionSortList().insertionSortList(p1);
        while (head != null){
            System.out.print(head.val + "\t");
            head = head.next;
        }
    }
}
