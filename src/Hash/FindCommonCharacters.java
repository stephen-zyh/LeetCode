package Hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1002
 * @date 2022/8/7 8:29
 */
public class FindCommonCharacters {
    public List<String> commonChars(String[] words) {
        int[] hash = new int[26];
        Arrays.fill(hash, 0);
        String firstWord = words[0];
        //统计第一个word中字符出现的情况，以此为基准，结果是其子集
        for (int i = 0; i < firstWord.length(); i++) {
            hash[firstWord.charAt(i) - 'a']++;
        }
        for (int i = 1; i < words.length; i++) {
            int[] otherWords = new int[26];
            //统计后续word中各字符的出现情况，相同字符的出现次数取小者（交集）
            for (int j = 0; j < words[i].length(); j++) {
                otherWords[words[i].charAt(j) - 'a']++;
            }
            for (int j = 0; j < hash.length; j++) {
                hash[j] = Math.min(hash[j], otherWords[j]);
            }
        }
        //结果转存到list中并返回
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < hash.length; i++) {
            for (int j = 0; j < hash[i]; j++) {
                result.add((String.valueOf((char)(i + 'a'))));
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        String[] words = {"bella","label","roller"};
        String[] words = {"cool","lock","cook"};
        List<String> result = new FindCommonCharacters().commonChars(words);
        System.out.println(Arrays.toString(result.toArray()));
    }
}
