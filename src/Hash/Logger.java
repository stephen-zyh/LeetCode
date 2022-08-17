package Hash;

import java.util.HashMap;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 359
 * @date 2022/8/6 23:04
 */
public class Logger {
    private final HashMap<String, Integer> map;
    public Logger() {
        map = new HashMap<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        //map中有这条日志，判断待插入的时间戳和已存在的时间戳之间的差值，如果小于10，则返回false；如果大于等于10，则更新时间戳为新的时间戳，返回true；
        //map中没有这条日志，插入这条日志，返回 true。
        if (map.containsKey(message) && timestamp - map.get(message) < 10){
            return false;
        }
        map.put(message, timestamp);
        return true;
    }

    public static void main(String[] args) {
        Logger logger = new Logger();
        System.out.println(logger.shouldPrintMessage(1, "foo")); //returns true;
        System.out.println(logger.shouldPrintMessage(2,"bar")); //returns true;
        System.out.println(logger.shouldPrintMessage(3,"foo")); //returns false;
        System.out.println(logger.shouldPrintMessage(8,"bar")); //returns false;
        System.out.println(logger.shouldPrintMessage(10,"foo")); //returns false;
        System.out.println(logger.shouldPrintMessage(11,"foo")); //returns true;
    }
}
