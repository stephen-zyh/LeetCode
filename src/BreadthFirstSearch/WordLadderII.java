package BreadthFirstSearch;

import java.util.*;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 126
 * @date 2022/9/26 20:32
 */
public class WordLadderII {
    Map<String, List<String>> graph;
    List<List<String>> result = new ArrayList<>();
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        graph = new HashMap<>();
        //建图，如果图的终点不是endWord，直接返回，否则对建好的图进行遍历求最短路径
        if (!buildGraph(beginWord, endWord, wordList)){
            return new ArrayList<>();
        }
        LinkedList<String> path = new LinkedList<>();
        path.add(endWord);
        addPath(path, beginWord, endWord);
        return result;
    }

    private boolean buildGraph(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        //wordList为空或者不包含endWord时，永远也不能到达endWord，返回0
        if (wordSet.size() == 0 || !wordList.contains(endWord)){
            return false;
        }

        boolean found = false;
        Set<String> visit = new HashSet<>();
        visit.add(beginWord);

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        //BFS
        while (!queue.isEmpty()){
            Set<String> subVisit = new HashSet<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currNode = queue.poll();
                for (String child : findChildren(currNode, wordSet, visit)){
                    List<String> parent = graph.getOrDefault(child, new ArrayList<>());
                    parent.add(currNode);
                    graph.put(child, parent);   //反向建图

                    if (!subVisit.contains(child)){
                        subVisit.add(child);
                        queue.offer(child); //将下一层的节点入队
                    }
                    //如果到达了endWord，不用再遍历下一层，直接跳出while循环
                    if (child.equals(endWord)){
                        found = true;
                    }
                }
            }
            visit.addAll(subVisit); //将每一层的节点标记为已访问
            if (found)  break;
        }
        return found;
    }

    //检查修改后的单词是否在wordList中
    private List<String> findChildren(String currNode, Set<String> wordSet, Set<String> visit){
        char[] word = currNode.toCharArray();
        List<String> children = new ArrayList<>();
        for (int i = 0; i < word.length; i++) {
            char originalChar = word[i];    //保存原来的字符，保证在修改单词后能改回原来的单词
            for (char j = 'a'; j <= 'z'; j++) {
                if (j == originalChar){
                    continue;
                }
                word[i] = j;
                String modifiedWord = String.valueOf(word);
                if (wordSet.contains(modifiedWord) && !visit.contains(modifiedWord)){
                    children.add(modifiedWord);
                }
            }
            word[i] = originalChar;
        }
        return children;
    }

    //添加路径，从endWord到beginWord进行DFS，避免遍历无用分支导致超时
    private void addPath(LinkedList<String> path, String beginWord, String currWord){
        if (currWord.equals(beginWord)){
            result.add(new ArrayList<>(path));
            return;
        }
        //DFS，回溯
        for (String next : graph.get(currWord)){
            path.addFirst(next);
            addPath(path, beginWord, next);
            path.removeFirst();
        }
    }

    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"));
        List<List<String>> result = new WordLadderII().findLadders(beginWord, endWord, wordList);
        System.out.println(result);
    }
}
