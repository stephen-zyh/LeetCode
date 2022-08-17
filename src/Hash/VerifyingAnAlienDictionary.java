package Hash;

import java.util.HashMap;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 953
 * @date 2022/8/6 22:31
 */
public class VerifyingAnAlienDictionary {
    HashMap<Character, Integer> orderMap = new HashMap<>();
    public boolean isAlienSorted(String[] words, String order) {
        //将字母与其顺序进行映射
        for (int i = 0; i < order.length(); i++) {
            orderMap.put(order.charAt(i), i);
        }
        //逐项比较
        for (int i = 0; i < words.length - 1; i++) {
            if (!compareTwo(words[i], words[i + 1])){
                return false;
            }
        }
        return true;
    }
    public boolean compareTwo(String word1, String word2){
        int i = 0, j = 0;
        //按照新的字典序进行比较
        while (i < word1.length() && j < word2.length()){
            if (orderMap.get(word1.charAt(i)) > orderMap.get(word2.charAt(j))){
                return false;
            }else if (orderMap.get(word1.charAt(i)) < orderMap.get(word2.charAt(j))){
                return true;
            }
            i++;
            j++;
        }
        return word1.length() <= word2.length();
    }

    public static void main(String[] args) {
//        String[] words = {"hello","leetcode"};
//        String order = "hlabcdefgijkmnopqrstuvwxyz";
        String[] words = {"word","world","row"};
        String order = "worldabcefghijkmnpqstuvxyz";
        boolean alienSorted = new VerifyingAnAlienDictionary().isAlienSorted(words, order);
        System.out.println(alienSorted);
    }
}
