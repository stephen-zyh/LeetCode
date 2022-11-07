package HashTable_DoublyLinkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 146
 * @date 2022/11/4 11:02
 */
public class LRUCache {
    class DoublyLinkedNode{
        int key, val;
        DoublyLinkedNode pre, next;
        public DoublyLinkedNode(int key, int value){
            this.key = key;
            val = value;
        }
    }
    Map<Integer, DoublyLinkedNode> map = new HashMap<>();
    int size, capacity;
    DoublyLinkedNode head, tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        size = 0;
        //所有节点的插入和删除都在head和tail之间进行
        head = new DoublyLinkedNode(0, 0);
        tail = new DoublyLinkedNode(0, 0);
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        //如果没有对应的key，直接返回；如果有对应的key，则将key对应的节点从双向链表中原来的位置移动到head之后（最近被访问过）
        if (!map.containsKey(key)){
            return -1;
        }
        DoublyLinkedNode curr = map.get(key);
        removeNode(key);
        addHead(curr);
        return curr.val;
    }

    public void put(int key, int value) {
        //如果关键字key已经存在，则变更其数据值value；如果不存在，则向缓存中插入该组 key-value
        DoublyLinkedNode curr = new DoublyLinkedNode(key, value);
        if (map.containsKey(key)){
            removeNode(key);
        }
        addHead(curr);
    }

    public void removeNode(int key){
        //修改key对应节点的前驱以及后继的指针的指向，使得无法在链表中找到node，即相当于node被移除
        DoublyLinkedNode node = map.get(key);
        DoublyLinkedNode pre = node.pre;
        DoublyLinkedNode next = node.next;
        pre.next = next;
        next.pre = pre;
        //注意修改链表当前大小，并在map中移除当前节点的key
        size--;
        map.remove(node.key);
    }

    public void addHead(DoublyLinkedNode node){
        //将node添加到head之后
        DoublyLinkedNode next = head.next;
        head.next = node;
        node.next = next;
        next.pre = node;
        node.pre = head;
        //注意修改链表当前大小，并在map中添加当前节点的key
        size++;
        map.put(node.key, node);
        //如果插入操作导致关键字数量超过capacity，则应该逐出最久未使用的关键字。
        if (size > capacity){
            DoublyLinkedNode preTail = tail.pre;
            removeNode(preTail.key);
        }
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // 返回 -1 (未找到)
        System.out.println(lRUCache.get(3));    // 返回 3
        System.out.println(lRUCache.get(4));    // 返回 4
    }
}
