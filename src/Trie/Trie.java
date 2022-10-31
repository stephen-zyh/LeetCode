package Trie;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 208
 * @date 2022/10/29 19:31
 */
public class Trie {
    static class TrieNode{
        boolean isEnd;  //标记当前字母是否是单词的结尾
        TrieNode[] children;    //下一个字母
        public TrieNode(){
            isEnd = false;
            children = new TrieNode[26];
        }
    }
    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
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
        TrieNode curr = root;
        for(char c : word.toCharArray()){
            int i = c - 'a';
            //出现了字典树中对应位置没有的字母，说明字典中不存在该词，返回false
            if (curr.children[i] == null){
                return false;
            }
            curr = curr.children[i];
        }
        //遍历完毕还需检查单词结尾，因为有可能查询的单词只是字典树中单词的一个前缀，而并非字典树中单词
        return curr.isEnd;
    }

    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for(char c : prefix.toCharArray()){
            int i = c - 'a';
            //出现了字典树中对应位置没有的字母，说明字典中不存在该前缀，返回false
            if (curr.children[i] == null){
                return false;
            }
            curr = curr.children[i];
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // 返回 True
        System.out.println(trie.search("app"));     // 返回 False
        System.out.println(trie.startsWith("app")); // 返回 True
        trie.insert("app");
        System.out.println(trie.search("app"));     // 返回 True
    }
}
