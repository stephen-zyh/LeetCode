package TopologicalSort;

import java.util.*;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 269
 * @date 2022/11/3 11:03
 */
public class AlienDictionary {
    Map<Character, Set<Character>> graph = new HashMap<>(); //映射的value是字典序在key之后的字母的集合
    int[] inDegree = new int[26];   //记录各个节点的入度
    public String alienOrder(String[] words) {
        buildGraph(words);

        Queue<Character> queue = new LinkedList<>();
        StringBuilder builder = new StringBuilder();

        topology(queue, builder);
        //如果能对出现的所有字符进行排序，说明存在合法的字典序，否则不能找到合法字典序
        return builder.length() == graph.size() ? builder.toString() : "";
    }

    //BFS，队列中是入度为0的节点
    private void topology(Queue<Character> queue, StringBuilder builder) {
        //入度为0的节点应当在出现的字母而非所有26个字母中找
        for (char c : graph.keySet()){
            if (inDegree[c - 'a'] == 0){
                queue.offer(c);
                builder.append(c);
            }
        }
        while (!queue.isEmpty()){
            char curr = queue.poll();
            //每次删除一个入度为0的节点，并更新其neighbor的入度信息，如果某个neighbor的入度变为0，将其入队。
            //builder中存储的即是出现的字母按外星字典序排列的结果
            for (char neighbor : graph.get(curr)){
                inDegree[neighbor - 'a']--;
                if (inDegree[neighbor - 'a'] == 0){
                    queue.offer(neighbor);
                    builder.append(neighbor);
                }
            }
        }
    }

    public void buildGraph(String[] words){
        //为所有单词中出现的所有字母建立一个映射，映射的value是字典序在key之后的字母的集合
        for (String word : words){
            int length = word.length();
            for (int i = 0; i < length; i++) {
                if (!graph.containsKey(word.charAt(i))){
                    graph.put(word.charAt(i), new HashSet<>());
                }
            }
        }
        for (int i = 1; i < words.length; i++) {
            String first = words[i - 1];
            String second = words[i];
            int min = Math.min(first.length(), second.length());
            for (int j = 0; j < min; j++) {
                //寻找first和second第一个不相同的字母，由于在列表中first排在second之前，故first和second第一个不相同的字母也遵从此顺序
                if (first.charAt(j) != second.charAt(j)){
                    char out = first.charAt(j);
                    char in = second.charAt(j);
                    //if判断是为了防止out指向的同一个节点在被添加多次从而导致in节点的inDegree错误
                    if (!graph.get(out).contains(in)){
                        graph.get(out).add(in);
                        inDegree[in - 'a']++;
                    }
                    //后续字母无需再作判断
                    break;
                }
                //如first = "ab", second = "a"，不可能有符合条件的字典序
                if (j == min - 1 && first.length() > second.length()){
                    graph.clear();
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
        String result = new AlienDictionary().alienOrder(words);
        System.out.println(result);
    }
}
