package BreadthFirstSearch;

import java.util.List;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 339和leetcode 364使用到的接口
 * @date 2022/9/26 10:53
 */
public interface NestedInteger {
//    public NestedInteger();//无参构造方法
//    public NestedInteger(int value);//构造方法
    public boolean isInteger();//判断此对象是否为Integer类型
    public Integer getInteger();//返回其中的Integer类型值
    public void setInteger(int value);//修改对象中的值
    public void add(NestedInteger ni);//为此对象中添加一个新的对象
    public List<NestedInteger> getList();//返回其中的list类型
}
