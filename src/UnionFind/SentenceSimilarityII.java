package UnionFind;

import java.util.*;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 737
 * @date 2022/11/1 21:17
 */
public class SentenceSimilarityII {
    Map<String, String> parent = new HashMap<>();
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        //句子长度不同，不可相似
        if (words1.length != words2.length){
            return false;
        }
        for (List<String> pair : pairs){
            //pair.get(0)没出现过，将其指向自己（pair.get(0)就是根）
            if (!parent.containsKey(pair.get(0))){
                parent.put(pair.get(0), pair.get(0));
            }
            String p0 = findParent(pair.get(0));    //找到pair.get(0)对应图的根
            if (!parent.containsKey(pair.get(1))){
                //pair.get(1)未出现过，将其直接与pair.get(0)的根（即p0）相连
                parent.put(pair.get(1), p0);
            }else {
                //pair.get(1)出现过，将其所在图的根指向pair.get(1)的根（即p0）
                String p1 = findParent(pair.get(1));
                parent.put(p1, p0);
            }
        }
        int length = words1.length;
        for (int i = 0; i < length; i++) {
            if (words1[i].equals(words2[i])){
                //words1[i]和words2[i]相等，无论words1[i]在pairs中是否存在，都可以直接开始下一轮比较
                continue;
            }else if (!parent.containsKey(words1[i]) || !parent.containsKey(words2[i])){
                //words1[i]和words2[i]不相等，如果words1[i]或words2[i]在parent中不存在映射，无法替换为相等，直接返回false
                return false;
            }
            String p1 = findParent(words1[i]);
            String p2 = findParent(words2[i]);
            //words1[i]和words2[i]不相等，words1[i]和words2[i]在parent中存在映射，比较二者的根是否相同，相同则继续下一轮，不等则返回false
            if (!p1.equals(p2)){
                return false;
            }
        }
        return true;
    }

    //寻找节点word的根，根的特征是自己指向自己
    public String findParent(String word){
        while (!parent.get(word).equals(word)){
            word = parent.get(word);
        }
        return word;
    }

    public static void main(String[] args) {
        String[] words1 = {"I","love","leetcode"}, words2 = {"I","love","onepiece"};
        List<List<String>> pairs = new ArrayList<>();
        pairs.add(new ArrayList<>(Arrays.asList("manga","onepiece")));
        pairs.add(new ArrayList<>(Arrays.asList("platform","anime")));
        pairs.add(new ArrayList<>(Arrays.asList("leetcode","platform")));
        pairs.add(new ArrayList<>(Arrays.asList("anime","manga")));
        boolean result = new SentenceSimilarityII().areSentencesSimilarTwo(words1, words2, pairs);
        System.out.println(result);
    }
}
