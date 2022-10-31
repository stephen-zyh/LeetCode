package Trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 642
 * @date 2022/10/31 10:19
 */
public class AutocompleteSystem {
    class TrieNode implements Comparable<TrieNode>{
        TrieNode[] children = new TrieNode[128];
        int times = 0;  //sentence结尾才有
        String s = null;    //sentence结尾才有
        List<TrieNode> top3 = new ArrayList<>();    //迄今排名前三的热词

        @Override
        public int compareTo(TrieNode o) {
            //按照出现次数排序，次数相同按照字典序排序
            if (this.times == o.times){
                return s.compareTo(o.s);
            }
            return o.times - this.times;
        }

        public void update(TrieNode node){
            //加入node后排序，并保留出现次数前三的单词
            if (!top3.contains(node)){
                top3.add(node);
            }
            Collections.sort(top3);
            if (top3.size() > 3){
                top3.remove(top3.size() - 1);
            }
        }
    }
    TrieNode root = new TrieNode();
    public void add(String sentence, int time){
        TrieNode curr = root;
        for(char c : sentence.toCharArray()){
            //对出现的字母对应的children创建对象
            if (curr.children[c] == null){
                curr.children[c] = new TrieNode();
            }
            curr = curr.children[c];
        }
        curr.s = sentence;
        curr.times += time;

        TrieNode last = curr;
        curr = root;
        //更新热词表，因为last中的s记录了整个sentence，所以更新时只用传入last就行
        for (char c : sentence.toCharArray()){
            curr = curr.children[c];
            curr.update(last);
        }
    }

    public AutocompleteSystem(String[] sentences, int[] times) {
        //构建字典树
        for (int i = 0; i < sentences.length; i++){
            add(sentences[i], times[i]);
        }
    }

    StringBuilder builder = new StringBuilder();
    TrieNode curr = root;
    public List<String> input(char c) {
        //本轮查询结束，将builder.toString()对应的次数加1，同时重置builder和curr，返回空列表
        if (c == '#'){
            add(builder.toString(), 1);
            builder = new StringBuilder();
            curr = root;
            return new ArrayList<>();
        }else {
            builder.append(c);
            //curr不为null，表明对应前缀存在，才能继续下一步的input
            //注意：此时已经对curr进行更新，因此随后判断是否有curr == null时，curr为这里的curr.children[c]
            if (curr != null){
                curr = curr.children[c];
            }
            if (curr == null){
                return new ArrayList<>();
            }

            List<String> result = new ArrayList<>();
            //将top3添加到结果中
            for (TrieNode node : curr.top3){
                result.add(node.s);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        String[] sentences = {"i love you", "island","ironman", "i love leetcode"};
        int[] times = {5,3,2,2};
        AutocompleteSystem autocompleteSystem = new AutocompleteSystem(sentences, times);
        System.out.println(autocompleteSystem.input('i'));
        System.out.println(autocompleteSystem.input(' '));
        System.out.println(autocompleteSystem.input('a'));
        System.out.println(autocompleteSystem.input('#'));
    }
}
