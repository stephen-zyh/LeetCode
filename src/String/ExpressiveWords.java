package String;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 809
 * @date 2022/7/13 22:51
 */
public class ExpressiveWords {
    public int expressiveWords(String s, String[] words) {
        char[] c = s.toCharArray();
        int count = 0, times = 1;
        int fast, slow = 0;
        ArrayList<int[]> list = new ArrayList<>();
        //用双指针记录s中各个出现的字母和对应的出现次数
        for (fast = 1; fast < c.length; fast++) {
            if (c[fast] == c[slow]){
                times++;
            }else {
                list.add(new int[]{c[slow], times});
                slow = fast;
                times = 1;
            }
        }
        //需要额外将s中的最后一个字母和其出现次数添加到list中
        list.add(new int[]{c[slow], times});

        for (String word : words) {
            char[] c1 = word.toCharArray();
            if (Arrays.equals(c1, c)) {
                continue;
            }
            slow = 0;
            fast = 1;
            times = 1;
            int i = 0;
            //类似地，统计word中的每个字母出现的次数，并与s逐一比较
            while (fast < c1.length && i < list.size()) {
                if (c1[fast] == c1[slow]) {
                    times++;
                } else {
                    //字母不相同，不可能扩张得到s，直接结束对word的遍历
                    if (list.get(i)[0] != c1[slow]){
                        break;
                    }else if (list.get(i)[1] < 3 && times != list.get(i)[1]) {
                        //s中对应字母出现次数小于3，该字母不可扩张，则word中对应字母出现次数应与s中相等
                        break;
                    }else if (times > list.get(i)[1]){
                        //word中对应字母出现次数比s多，不可扩张
                        break;
                    }else{
                        //为继续比较下一个字母做准备
                        slow = fast;
                        times = 1;
                        i++;
                    }
                }
                fast++;
            }
            //与上个while中的else情况类似
            if (i < list.size()){
                if (list.get(i)[0] != c1[slow]){
                    continue;
                }else if (list.get(i)[1] < 3 && times != list.get(i)[1]) {
                    continue;
                }else if (times > list.get(i)[1]){
                    continue;
                }else{
                    i++;
                }
            }
            //逐字母比较完毕，可以扩张得到s
            if (fast == c1.length && i == list.size()){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String s = "hhheeellooo";
//        String[] words = {"heeelloooworld"};
        String[] words = {"hi","hello","hellooo","heeello","hhhello","hhheeello"};
        int count = new ExpressiveWords().expressiveWords(s, words);
        System.out.println(count);
    }
}
