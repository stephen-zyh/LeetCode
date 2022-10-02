package BreadthFirstSearch;

import java.util.*;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 127
 * @date 2022/9/26 19:01
 */
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        //wordList为空或者不包含endWord时，永远也不能到达endWord，返回0
        if (wordSet.size() == 0 || !wordList.contains(endWord)){
            return 0;
        }
        wordSet.remove(beginWord);
        Set<String> visit = new HashSet<>();    //记录访问过的单词
        visit.add(beginWord);

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        int step = 1;
        //BFS
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currWord = queue.poll();
                if (containsModifiedWord(currWord, endWord, wordSet, visit, queue)){
                    return ++step;
                }
            }
            step++;
        }
        return 0;
    }
    //检查修改后的单词是否在wordList中
    private boolean containsModifiedWord(String currWord, String endWord, Set<String> wordSet, Set<String> visit, Queue<String> queue){
        char[] word = currWord.toCharArray();
        for (int i = 0; i < endWord.length(); i++) {
            char originalChar = word[i];    //保存原来的字符，保证在修改单词后能改回原来的单词
            for (char j = 'a'; j <= 'z'; j++) {
                if (j == originalChar){
                    continue;
                }
                word[i] = j;
                String modifiedWord = String.valueOf(word);
                if (wordSet.contains(modifiedWord)){
                    if (modifiedWord.equals(endWord)){
                        return true;
                    }
                    if (!visit.contains(modifiedWord)){
                        queue.offer(modifiedWord);
                        visit.add(modifiedWord);
                    }
                }
            }
            word[i] = originalChar;
        }
        return false;
    }

    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        ArrayList<String> wordList = new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"));

        int result = new WordLadder().ladderLength(beginWord, endWord, wordList);
        System.out.println(result);
    }
}
