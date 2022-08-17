package LinkedList;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 725
 * @date 2022/7/24 21:58
 */
public class SplitLinkedListInParts {
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode test = head;
        int len = 0;
        //遍历链表得到链表长度
        while (test != null){
            len++;
            test = test.next;
        }
        int remains = len % k;
        int averagelen = len / k;
        ListNode[] result = new ListNode[k];
        ListNode subhead = head;
        //前remains份长度均为averagelen + 1
        for (int i = 0; i < remains; i++) {
            int count = 0;
            result[i] = subhead;
            ListNode pre = null;
            while (count != averagelen + 1 && subhead != null){
                pre = subhead;
                subhead = subhead.next;
                count++;
            }
            //每一段的尾部指向null，即将原链表断开
            if (pre != null){
                pre.next = null;
            }
        }
        //后续部分长度均为averagelen
        for (int i = remains; i < k; i++) {
            int count = 0;
            result[i] = subhead;
            ListNode pre = null;
            while (count != averagelen && subhead != null){
                pre = subhead;
                subhead = subhead.next;
                count++;
            }
            if (pre != null){
                pre.next = null;
            }
        }
        return result;
    }
}
