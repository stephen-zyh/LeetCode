package String;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 824
 * @date 2022/7/9 9:24
 */
public class GoatLatin {
    public String toGoatLatin(String sentence) {
        //拆分句子中各个单词
        String[] words = sentence.split(" ");
        StringBuilder builder = new StringBuilder();
        String aeiou = "aeiouAEIOU";
        for (int i = 0; i < words.length; i++) {
            if (aeiou.indexOf(words[i].charAt(0)) == -1) {
                //如果单词首字母不是元音字母，则移除第一个字符并将它放到末尾，之后再添加"ma"
                builder.append(words[i].substring(1)).append(words[i].charAt(0)).append("ma");
            }else{
                //单词首字母以元音开头，在单词后添加"ma"
                builder.append(words[i]).append("ma");
            }
            //根据单词在句子中的索引，在单词最后添加与索引相同数量的字母'a'，索引从1开始
            builder.append("a".repeat(i + 1));
            //改后的单词仍然要用空格隔开
            builder.append(" ");
        }
        //去掉最右边的空格再返回
        return builder.toString().trim();
    }

    public static void main(String[] args) {
//        String sentence = "I speak Goat Latin";
        String sentence = "The quick brown fox jumped over the lazy dog";
        System.out.println(new GoatLatin().toGoatLatin(sentence));
    }
}
