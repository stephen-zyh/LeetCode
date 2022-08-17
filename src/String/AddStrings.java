package String;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 415
 * @date 2022/7/9 10:56
 */
public class AddStrings {
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;  //carry用以记录进位情况
        StringBuilder builder = new StringBuilder();
        while (i >= 0 || j >= 0){
            //倒序遍历两个字符串，sum用以记录两个字符串相同位上的和
            int sum = 0;
            if (i >= 0){
                sum += (num1.charAt(i) - '0');
                i--;
            }
            if (j >= 0){
                sum += (num2.charAt(j) - '0');
                j--;
            }
            sum += carry;
            builder.append(sum % 10);
            carry = sum / 10;
        }
        //加法完成后，如果仍有进位位，应将其加入到builder中
        if (carry != 0){
            builder.append(carry);
        }
        //由于倒序相加，返回的结果应当反转
        return builder.reverse().toString();
    }

    public static void main(String[] args) {
//        String num1 = "11", num2 = "123";
        String num1 = "456", num2 = "77";
//        String num1 = "0", num2 = "0";
        System.out.println(new AddStrings().addStrings(num1, num2));
    }
}
