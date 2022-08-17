package Hash;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 966
 * @date 2022/8/10 22:14
 */
public class VowelSpellchecker {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        HashSet<String> wordSet = new HashSet<>();
        HashMap<String, String> words = new HashMap<>();
        String[] result = new String[queries.length];

        Collections.addAll(wordSet, wordlist);  //wordSet中是wordlist包含的单词，查询时优先查询wordSet

        for(String word : wordlist){
            //忽略大小写的单词s和原单词的映射，只取第一次的映射（第一次匹配）
            String s = word.toLowerCase();
            if (!words.containsKey(s)){
                words.put(s, word);
            }
            //修改元音字母的单词和原单词的映射，只取第一次映射
            String s1 = word.toLowerCase().replace('a', '*').replace('e', '*').
                    replace('i', '*').replace('o', '*').replace('u', '*');
            if (!words.containsKey(s1)){
                words.put(s1, word);
            }
        }

        for (int i = 0; i < result.length; i++) {
            //完全匹配
            if (wordSet.contains(queries[i])){
                result[i] = queries[i];
                continue;
            }
            //忽略大小写、在wordlist中的第一个匹配
            String secondCase = queries[i].toLowerCase();
            if (words.containsKey(secondCase)){
                result[i] = words.get(secondCase);
                continue;
            }
            //忽略元音、在wordlist中的第一个匹配，以及没有找到匹配的情况
            String thirdCase = queries[i].toLowerCase().replace('a', '*').replace('e', '*').
                    replace('i', '*').replace('o', '*').replace('u', '*');
            if (words.get(thirdCase) != null){
                result[i] = words.get(thirdCase);
            }else {
                result[i] = "";
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] wordlist = {"KiTe","kite","hare","Hare"};
        String[] queries = {"kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"};

        String[] result = new VowelSpellchecker().spellchecker(wordlist, queries);
        System.out.println(Arrays.toString(result));
    }
}
