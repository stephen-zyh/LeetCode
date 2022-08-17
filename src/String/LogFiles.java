package String;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 937
 * @date 2022/7/9 8:39
 */
public class LogFiles {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (o1, o2) ->{
            char c1 = o1.charAt(o1.length() - 1);
            char c2 = o2.charAt(o2.length() - 1);
            if (Character.isLetter(c1) && Character.isLetter(c2)){
                String body1 = o1.substring(o1.indexOf(" "));
                String body2 = o2.substring(o2.indexOf(" "));
                //字母日志在内容相同时，按标识符排序
                if (Objects.equals(body1, body2)){
                    return o1.compareTo(o2);
                }else {
                    //字母日志在内容不同时，忽略标识符后，按内容字母顺序排序
                    return body1.compareTo(body2);
                }
            }else if (Character.isDigit(c1) && Character.isDigit(c2)){
                //数字日志应该保留原来的相对顺序
                return 0;
            }else if (Character.isDigit(c1)){
                //所有字母日志都排在数字日志之前。
                return 1;
            }else {
                return -1;
            }
        });
        return logs;
    }

    public static void main(String[] args) {
//        String[] logs = {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
        String[] logs = {"a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"};
        System.out.println(Arrays.toString(new LogFiles().reorderLogFiles(logs)));
    }
}
