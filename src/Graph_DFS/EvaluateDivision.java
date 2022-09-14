package Graph_DFS;

import java.util.*;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 339
 * @date 2022/9/12 9:48
 */
public class EvaluateDivision {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();   //用以存储字符串表达式与值之间的关系
        double[] result = new double[queries.size()];
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);

            String start = equation.get(0);
            String end = equation.get(1);
            //给出start/end的值相当于也给出了end/start的值，因此需要两个映射
            graph.putIfAbsent(start, new HashMap<>());
            graph.get(start).put(end, values[i]);

            graph.putIfAbsent(end, new HashMap<>());
            graph.get(end).put(start, 1 / values[i]);
        }
        for (int i = 0; i < queries.size(); i++) {
            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);
            //如果查询的表达式里面有已知表达式未出现的字母，不可能运算得到结果，将result[i]赋值为-1，否则调用getValue求值
            if (!graph.containsKey(start) || !graph.containsKey(end)){
                result[i] = -1;
            }else {
                Set<String> visit = new HashSet<>();    //以访问的点的集合
                result[i] = getValue(start, end, graph, visit);
            }
        }
        return result;
    }
    private double getValue(String start, String end, Map<String, Map<String, Double>> graph, Set<String> visit){
        visit.add(start);
        Map<String, Double> map = graph.get(start);
        if (graph.get(start).containsKey(end)){
            //直接求得
            return graph.get(start).get(end);
        }else {
            //通过邻居间接求得
            for (Map.Entry<String, Double> entry : map.entrySet()){
                if (!visit.contains(entry.getKey())){
                    double nextValue = getValue(entry.getKey(), end, graph, visit);
                    if (nextValue > 0){
                        return entry.getValue() * nextValue;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("a","b"));
        equations.add(Arrays.asList("b","c"));
        double[] values = {2.0,3.0};
        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("a","c"));
        queries.add(Arrays.asList("b","a"));
        queries.add(Arrays.asList("a","e"));
        queries.add(Arrays.asList("a","a"));
        queries.add(Arrays.asList("x","x"));
        double[] result = new EvaluateDivision().calcEquation(equations, values, queries);
        System.out.println(Arrays.toString(result));
    }
}
