package String;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 68
 * @date 2022/7/16 22:05
 */
public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int left;
        int right = 0;
        while (true){
            int sumLen = 0;
            left = right;
            //统计一行能放多少单词，一行的单词数目为right -left
            while (right < words.length && sumLen + words[right].length() + right - left <= maxWidth){
                sumLen += words[right].length();
                right++;
            }
            //当前行是最后一行：单词左对齐，且单词之间应只有一个空格，在行末填充剩余空格；
            if (right == words.length){
                StringBuilder builder = new StringBuilder();
                int len1 = 0;
                //在n个单词间添加n-1个空格
                for (int i = left; i < right - 1; i++) {
                    builder.append(words[i]).append(" ");
                    len1 += words[i].length() + 1;
                }
                builder.append(words[right - 1]);
                len1 += words[right - 1].length();
                //若还有剩余空间，填充空格
                if (maxWidth - len1 > 0){
                    builder.append(" ".repeat(maxWidth - len1));
                }
                result.add(builder.toString());
                return result;
            }
            //当前行不是最后一行，且只有一个单词：该单词左对齐，在行末填充空格；
            if (right - left == 1){
                result.add(words[left] + " ".repeat(maxWidth - words[left].length()));
            }else {
                StringBuilder builder = new StringBuilder();
                //averageBlank个空格先平均分配到right - left个单词的right - left - 1
                int averageBlank = (maxWidth - sumLen) / (right - left - 1);
                //余下extraBlank个空格均匀填充到前extraBlank + 1个单词之间
                int extraBlank = maxWidth - sumLen - (right - left - 1) * averageBlank;
                for (int i = left; i < left + extraBlank; i++) {
                    builder.append(words[i]).append(" ".repeat(averageBlank + 1));
                }
                for (int i = left + extraBlank; i < right - 1; i++) {
                    builder.append(words[i]).append(" ".repeat(averageBlank));
                }
                builder.append(words[right - 1]);
                result.add(builder.toString());
            }
        }
    }

    public static void main(String[] args) {
//        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
//        String[] words = {"What","must","be","acknowledgment","shall","be"};
        String[] words = {"ask","not","what","your","country","can","do","for","you","ask","what","you","can","do","for","your","country"};
        int maxWidth = 16;
        List<String> list = new TextJustification().fullJustify(words, maxWidth);
        Object[] array = list.toArray();
        for (Object o : array) {
            System.out.println("|" + o + "|");
        }
    }
}
