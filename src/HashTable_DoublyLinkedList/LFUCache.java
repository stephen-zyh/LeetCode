package HashTable_DoublyLinkedList;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 460
 * @date 2022/11/4 13:20
 */
public class LFUCache {
    HashMap<Integer, Integer> values;   //key-value的映射
    HashMap<Integer, Integer> counts;   //key-count的映射，存储key出现的频率
    HashMap<Integer, LinkedHashSet<Integer>> lists; //频率和对应频率的节点集的映射
    int capacity;
    int min = -1;   //频率最低的key出现的次数
    public LFUCache(int capacity) {
        this.capacity = capacity;
        values = new HashMap<>();
        counts = new HashMap<>();
        lists = new HashMap<>();
    }

    public int get(int key) {
        //如果没有对应的key，直接返回；
        if (!values.containsKey(key)){
            return -1;
        }
        int count = counts.get(key);
        counts.put(key, count + 1); //出现频率加一
        lists.get(count).remove(key);   //将其从出现频率为count的节点集中删除
        //如果访问之前的该节点就是出现频率最低的节点并且没有和访问前的该节点频率相同的节点，访问后该节点仍是频率最低节点，要将min自增
        if (count == min && lists.get(count).size() == 0){
            min++;
        }
        //将访问后的该节点放入出现频率为count+1的节点集中
        if (!lists.containsKey(count + 1)){
            lists.put(count + 1, new LinkedHashSet<>());
        }
        lists.get(count + 1).add(key);
        return values.get(key);
    }

    public void put(int key, int value) {
        if (capacity == 0){
            return;
        }
        //key在之前访问过，此处修改key对应的value值，对于其在count和lists中的映射值，直接调用get修改即可（put本身就相当于进行了一次访问）
        if (values.containsKey(key)){
            values.put(key, value);
            get(key);
            return;
        }
        //当缓存达到其容量capacity时，则应该在插入新项之前，移除最不经常使用的项
        if (values.size() >= capacity){
            //当存在平局（即两个或更多个键具有相同使用频率）时，应该去除最近最久未使用的键。
            int minFreqKey = lists.get(min).iterator().next();
            lists.get(min).remove(minFreqKey);
            values.remove(minFreqKey);
            counts.remove(minFreqKey);
        }
        //如果键不存在，插入键值对。
        values.put(key, value);
        counts.put(key, 1);
        min = 1;
        if (!lists.containsKey(1)){
            lists.put(1, new LinkedHashSet<>());
        }
        lists.get(1).add(key);
    }

    public static void main(String[] args) {
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
        lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        System.out.println(lfu.get(1));// 返回 1
        // cache=[1,2], cnt(2)=1, cnt(1)=2
        lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
        // cache=[3,1], cnt(3)=1, cnt(1)=2
        System.out.println(lfu.get(2));      // 返回 -1（未找到）
        System.out.println(lfu.get(3));      // 返回 3
        // cache=[3,1], cnt(3)=2, cnt(1)=2
        lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
        // cache=[4,3], cnt(4)=1, cnt(3)=2
        System.out.println(lfu.get(1));      // 返回 -1（未找到）
        System.out.println(lfu.get(3));      // 返回 3
        // cache=[3,4], cnt(4)=1, cnt(3)=3
        System.out.println(lfu.get(4));      // 返回 4
        // cache=[3,4], cnt(4)=2, cnt(3)=3
    }
}
