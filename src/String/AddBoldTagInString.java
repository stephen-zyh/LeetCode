package String;

import java.util.Arrays;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 616
 * @date 2022/7/14 21:43
 */
public class AddBoldTagInString {
    public String addBoldTag(String s, String[] dict) {
        boolean[] flag = new boolean[s.length()];
        Arrays.fill(flag, false);
        StringBuilder builder = new StringBuilder();
        for (String d: dict) {
            int start = 0, end = 0, target = 0;
            while (target < s.length()){
                start = s.indexOf(d, target++);
                if (start == -1){
                    break;
                }
                end = start + d.length();
                for (int i = start; i < end; i++) {
                    flag[i] = true;
                }
            }
        }
        int i = 0;
        if (flag[0]){
            builder.append("<b>");
        }
        builder.append(s.charAt(0));
        for (i = 1; i < s.length(); i++) {
            if (!flag[i] && flag[i - 1]){
                builder.append("</b>");
            }else if (flag[i] && !flag[i - 1]){
                builder.append("<b>");
            }
            builder.append(s.charAt(i));
        }
        if (flag[s.length() - 1]){
            builder.append("</b>");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
//        String s = "abcxyz123";
//        String[] dict = {"abc","123"};
//        String s = "aaabbcc";
//        String[] dict = {"aaa","ab","bc"};
        String s = "qrzjsorbkmyzzzvoqxefvxkcwtpkhzbakuufbpgdkykmojwuennrjeciqvvacpzrrczfhxnsmginzwinzihpomxtmwey" +
                "yzzmgcoiupjnidphvzlnxtcogufozlenjfvokztghwckzyvmktduqkizixzxpanj" +
                "wrdeudjyftxksjgdklwxrhmudhrtemuvelykqaafzlqmennttkighcdxfozdcoqkyshhajipnsdrljrnlwmyjuwxsebpqm";
        String[] dict = {"qr","zj","so","rb","km","yz","zz","vo","qx","ef","vx","kc","wt","pk"};
        System.out.println(new AddBoldTagInString().addBoldTag(s, dict));
        System.out.println("<b>qrzjsorbkmyzzzvoqxefvxkcwtpk</b>hzbakuufbpgdky<b>km</b>ojwuennrjeciqvvacpzrrczfhxns" +
                "mginzwinzihpomxtmwey<b>yzz</b>mgcoiupjnidphvzlnxtcogufozlenjf<b>vo</b>kztghwckzyvmktduqkizixzxpan" +
                "jwrdeudjyftxksjgdklwxrhmudhrtemuvelykqaafzlqmennttkighcdxfozdcoqkyshhajipnsdrljrnlwmyjuwxsebpqm");
    }
}
