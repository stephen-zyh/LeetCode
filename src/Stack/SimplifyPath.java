package Stack;

import java.util.Stack;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 71
 * @date 2022/8/1 14:21
 */
public class SimplifyPath {
    public String simplifyPath(String path) {
        String[] subPath = path.split("/"); //以'/'作分隔符将字符串分开
        Stack<String> pathStack = new Stack<>();
        for (String s : subPath) {
            //表示本级目录或者目录名为空，可直接忽略
            if (s.equals(".") || s.equals("")) {
                continue;
            }
            //如果有上级目录，就回到上级目录（将当前目录出栈）
            if (s.equals("..")){
                if (!pathStack.isEmpty()) {
                    pathStack.pop();
                }
            }else {
                //一般的文件目录名的情况
                pathStack.push(s);
            }
        }
        StringBuilder builder = new StringBuilder();
        //栈中内容重新取出，组成简化后的路径名
        while (!pathStack.isEmpty()){
            builder.insert(0, pathStack.pop());
            builder.insert(0, "/");
        }
        if (builder.isEmpty()){
            builder.append("/");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
//        String path = "/home/";
//        String path = "/../";
//        String path = "/home//foo/";
        String path = "/a/./b/../../c/";
        String result = new SimplifyPath().simplifyPath(path);
        System.out.println(result);
    }
}
