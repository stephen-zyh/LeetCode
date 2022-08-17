package String;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 443
 * @date 2022/7/11 10:45
 */
public class StringCompression {
    public int compress(char[] chars) {
        //设置slow和fast一快一慢两个指针，count用于记录一个元素出现的次数
        int slow = 0, fast = 1, count = 1;
        StringBuilder builder = new StringBuilder();
        while (fast < chars.length){
            if (chars[fast] == chars[slow]){
                count++;
            }else if (count == 1){
                //出现次数为1时，不用添加其出现的次数
                builder.append(chars[slow]);
                slow++;
            }else {
                builder.append(chars[slow]).append(count);
                //上一个字符出现次数记录完成后，移到下一个字符出现处，重新开始计数
                slow = fast;
                count = 1;
            }
            fast++;
        }
        //循环结束，需要将最后一个字符及其出现次数添加到builder中
        if (count == 1){
            builder.append(chars[slow]);
        }else {
            builder.append(chars[slow]).append(count);
        }
        int len = 0;
        for (char c : builder.toString().toCharArray()) {
            chars[len++] = c;
        }
        return len;
    }

    public static void main(String[] args) {
//        char[] chars = {'a','a','b','b','c','c','c'};
//        char[] chars = {'a','b','b','b','b','b','b','b','b','b','b','b','b'};
        char[] chars = {'a'};
        int len = new StringCompression().compress(chars);
        System.out.println(len);
        for (int i = 0; i < len; i++) {
            System.out.print(chars[i] + " ");
        }
    }
}
