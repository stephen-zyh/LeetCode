package Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 23
 * @date 2022/8/29 22:34
 */
public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        //将lists中所有元素加入到堆中
        for(ListNode list : lists){
            while (list != null){
                queue.offer(list);
                list = list.next;
            }
        }
        return listBuilder(queue);
    }

    private ListNode listBuilder(PriorityQueue<ListNode> queue){
        //base case
        if (queue.isEmpty()){
            return null;
        }
        ListNode currNode = queue.poll();
        currNode.next = listBuilder(queue);
        return currNode;
    }
}
