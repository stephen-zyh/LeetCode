package BreadthFirstSearch;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 364，参考leetcode 339
 * @date 2022/9/26 11:07
 */
public class NestedListWeightSumII {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        //特殊情况
        if (nestedList == null){
            return 0;
        }
        Queue<NestedInteger> queue = new LinkedList<>(nestedList);
        int weight = 0, currSum = 0;
        //BFS
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NestedInteger curr = queue.poll();
                //如果当前对象是整数，在本层处理；否则入队，在下一层处理
                if (curr.isInteger()){
                    currSum += curr.getInteger();
                }else {
                    queue.addAll(curr.getList());
                }
            }
            //每加一次currSum，各层权重都会加1
            weight += currSum;
        }
        return weight;
    }
}
