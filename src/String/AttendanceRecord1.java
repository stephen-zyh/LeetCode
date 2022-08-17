package String;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 551
 * @date 2022/7/7 15:43
 */
public class AttendanceRecord1 {
    public boolean checkRecord(String s) {
        char[] record = s.toCharArray();
        int countL = 0, countA = 0;
        for (char c : record) {
            if (c == 'L') {
                countL++;
                if (countL >= 3) {
                    return false;
                }
            } else if (c == 'A') {
                countA++;
                countL = 0;
            } else {
                countL = 0;
            }
        }
        return countA < 2;
    }

    public static void main(String[] args) {
        String s = "PPALLP";
//        String s = "PPALLL";
        System.out.println(new AttendanceRecord1().checkRecord(s));
    }
}
