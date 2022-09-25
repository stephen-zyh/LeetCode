package BinarySearch;

import java.util.*;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1055
 * @date 2022/9/24 16:51
 */
public class ShortestWayToFormString {
    public int shortestWay(String s, String t) {
        Map<Character, List<Integer>> map = new HashMap<>();
        //记录每种字符出现的位置
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.putIfAbsent(c, new ArrayList<>());
            map.get(c).add(i);
        }
        int result = 1, index = -1;
        for (int i = 0; i < t.length(); i++) {
            int nextIndex = searchCharacter(map, index, t.charAt(i));   //在下标index后找对应字符出现的位置
            if (nextIndex == -1 && index == -1){
                //在更新之前的index之后没有找到对应字符，且从头开始寻找也没有找到，返回-1
                return -1;
            }else if (nextIndex == -1){
                //在index后没找到，需要再从头寻找（循环增加一轮，在index = -1后的元素中寻找），并且由于没找到，i不自增
                result++;
                index = -1;
                i--;
            }else {
                //正常情况，在index后找到了对应的字符
                index = nextIndex;
            }
        }
        return result;
    }

    private int searchCharacter(Map<Character, List<Integer>> map, int index, char target){
        if (!map.containsKey(target)){
            return -1;
        }
        List<Integer> list = map.get(target);
        int left = 0, right = list.size() - 1;
        //二分法查找满足条件的最小的下标
        while (left < right){
            int middle = left + (right - left) / 2;
            if (list.get(middle) <= index){
                left = middle + 1;
            }else {
                right = middle;
            }
        }
        return list.get(left) > index ? list.get(left) : -1;
    }

    public static void main(String[] args) {
        String source = "abc", target = "acdbc";
        int result = new ShortestWayToFormString().shortestWay(source, target);
        System.out.println(result);
    }
}
