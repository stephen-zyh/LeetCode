package Hash;

import java.util.*;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 49
 * @date 2022/8/8 13:33
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        HashMap<String, ArrayList<String>> map = new HashMap<>();   //排序后的字母作索引，对应单词为元素
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = Arrays.toString(chars);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }
        //结果转存到result列表中
        Set<String> keySet = map.keySet();
        for(String key : keySet){
            result.add(map.get(key));
        }
        return result;
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = new GroupAnagrams().groupAnagrams(strs);
        System.out.println(result);
    }
}
