package Trie;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 720
 * @date 2022/10/31 8:30
 */
public class LongestWordInDictionary {
    static class TrieNode {
        boolean isEnd;  //标记当前字母是否是单词的结尾
        TrieNode[] children;    //下一个字母

        public TrieNode() {
            isEnd = false;
            children = new TrieNode[26];
        }
    }

    TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            //对出现的字母对应的children创建对象
            if (curr.children[i] == null) {
                curr.children[i] = new TrieNode();
            }
            curr = curr.children[i];
        }
        //单词添加完毕，标记结尾
        curr.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            curr = curr.children[i];
            //如果当前查询的单词不是由words词典中其他单词逐步添加一个字母组成，则不能作为结果，直接返回false。
            if (!curr.isEnd) {
                return false;
            }
        }
        return true;
    }

    public String longestWord(String[] words) {
        for (String word : words){
            insert(word);
        }
        String result = "";
        for (String word : words){
            int m = result.length(), n = word.length();
            if (m > n)  continue;   //当前遍历的单词长度不够
            if (m == n && result.compareTo(word) < 0)   continue;   //长度够了，不符合字典序
            if (search(word))   result = word;  //符合长度和字典序要求，且由words词典中其他单词逐步添加一个字母组成，更新结果
        }
        return result;
    }

    public static void main(String[] args) {
        String[] words = {"w","wo","wor","worl", "world"};
        String result = new LongestWordInDictionary().longestWord(words);
        System.out.println(result);
    }
}
