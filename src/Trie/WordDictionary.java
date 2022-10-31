package Trie;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 211
 * @date 2022/10/31 8:59
 */
public class WordDictionary {
    static class TrieNode {
        boolean isEnd;  //标记当前字母是否是单词的结尾
        TrieNode[] children;    //下一个字母

        public TrieNode() {
            isEnd = false;
            children = new TrieNode[26];
        }
    }
    TrieNode root;
    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode curr = root;
        for(char c : word.toCharArray()){
            int i = c - 'a';
            //对出现的字母对应的children创建对象
            if (curr.children[i] == null){
                curr.children[i] = new TrieNode();
            }
            curr = curr.children[i];
        }
        //单词添加完毕，标记结尾
        curr.isEnd = true;
    }

    public boolean search(String word) {
        return search(word, 0, root);
    }

    //DFS
    private boolean search(String word, int index, TrieNode node){
        if (node == null)   return false;   //当前字符不存在，返回false
        if (index >= word.length()) return node.isEnd;  //遍历完整个字符串

        char curr = word.charAt(index);
        if (curr == '.'){
            for (TrieNode child : node.children){
                if (search(word, index + 1, child)){
                    return true;
                }
            }
            return false;
        }else {
            return search(word, index + 1, node.children[curr - 'a']);
        }
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad")); // 返回 False
        System.out.println(wordDictionary.search("bad")); // 返回 True
        System.out.println(wordDictionary.search(".ad")); // 返回 True
        System.out.println(wordDictionary.search("b..")); // 返回 True
    }
}
