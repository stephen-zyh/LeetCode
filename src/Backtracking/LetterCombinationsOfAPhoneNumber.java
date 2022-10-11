package Backtracking;

import java.util.*;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 17
 * @date 2022/10/8 12:39
 */
public class LetterCombinationsOfAPhoneNumber {
    Map<Character, List<Character>> map = new HashMap<>();
    public List<String> letterCombinations(String digits) {
        //建立数字到字母之间的映射
        map.put('2', Arrays.asList('a','b','c'));
        map.put('3', Arrays.asList('d','e','f'));
        map.put('4', Arrays.asList('g','h','i'));
        map.put('5', Arrays.asList('j','k','l'));
        map.put('6', Arrays.asList('m','n','o'));
        map.put('7', Arrays.asList('p','q','r','s'));
        map.put('8', Arrays.asList('t','u','v'));
        map.put('9', Arrays.asList('w','x','y','z'));
        List<String> result = new ArrayList<>();
        calculateCombination(digits, 0, new StringBuilder(), result);
        return result;
    }

    private void calculateCombination(String digits, int index, StringBuilder current, List<String> result){
        //遍历到结尾digits的结尾，说明产生了一个结果，将结果返回
        if (index == digits.length()){
            //当digits.length()为0时，应该直接返回空列表，而非空字符串
            if (digits.length() != 0){
                result.add(current.toString());
            }
            return;
        }
        List<Character> list = map.get(digits.charAt(index));   //对于电话号码的每一位，找到其对应的字母
        for (int i = 0; i < list.size(); i++) {
            //回溯
            current.append(list.get(i));
            calculateCombination(digits, index + 1, current, result);
            current.deleteCharAt(current.length() - 1);
        }
    }

    public static void main(String[] args) {
        String digits = "23";
        List<String> result = new LetterCombinationsOfAPhoneNumber().letterCombinations(digits);
        System.out.println(result);
    }
}
