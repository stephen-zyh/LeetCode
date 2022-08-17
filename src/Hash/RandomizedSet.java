package Hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 380
 * @date 2022/8/8 10:36
 */
public class RandomizedSet {
    private final HashMap<Integer,Integer> map;
    private final ArrayList<Integer> list;
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    public boolean insert(int val) {
        //将不存在于list中的元素添加到list末尾，同时在map中记录对应索引
        if (!map.containsKey(val)){
            map.put(val, list.size());
            list.add(val);
            return true;
        }
        return false;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)){
            return false;
        }
        //要删除的元素被末尾元素覆盖，再删除末尾元素，更新map，这样可以保证map中记录的索引一直与list中匹配
        int index = map.get(val);
        int last = list.get(list.size() - 1);
        list.set(index, last);
        if (list.size() != 0){
            map.put(list.get(index), index);
        }
        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }

    public int getRandom() {
        return list.get(new Random().nextInt(list.size()));
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        System.out.println(randomizedSet.insert(1)); // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
        System.out.println(randomizedSet.remove(2)); // 返回 false ，表示集合中不存在 2。
        System.out.println(randomizedSet.insert(2)); // 向集合中插入 2 。返回 true 。集合现在包含 [1,2]。
        System.out.println(randomizedSet.getRandom()); // getRandom 应随机返回 1 或 2 。
        System.out.println(randomizedSet.remove(1)); // 从集合中移除 1 ，返回 true 。集合现在包含 [2]。
        System.out.println(randomizedSet.insert(2)); // 2 已在集合中，所以返回 false 。
        System.out.println(randomizedSet.getRandom()); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2。
    }
}
