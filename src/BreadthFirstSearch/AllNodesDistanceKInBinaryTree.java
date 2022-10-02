package BreadthFirstSearch;

import java.util.*;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 863
 * @date 2022/9/28 21:46
 */
public class AllNodesDistanceKInBinaryTree {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        //特殊情况，距离为0，返回节点本身
        if (k == 0){
            List<Integer> result = new ArrayList<>();
            result.add(target.val);
            return result;
        }
        //BFS实现树转图
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Queue<TreeNode> queue1 = new LinkedList<>();
        queue1.offer(root);
        while (!queue1.isEmpty()){
            int size = queue1.size();
            for (int i = 0; i < size; i++) {
                TreeNode currNode = queue1.poll();

                int value = currNode.val;
                graph.putIfAbsent(value, new ArrayList<>());

                TreeNode left = currNode.left;
                TreeNode right = currNode.right;
                //检验子节点非空后才将其纳入到图中
                if (left != null){
                    graph.get(value).add(left.val);
                    graph.putIfAbsent(left.val, new ArrayList<>());
                    graph.get(left.val).add(value);
                    queue1.offer(left);
                }
                if (right != null){
                    graph.get(value).add(right.val);
                    graph.putIfAbsent(right.val, new ArrayList<>());
                    graph.get(right.val).add(value);
                    queue1.offer(right);
                }
            }
        }

        Set<Integer> visit = new HashSet<>();
        visit.add(target.val);

        List<Integer> result = new ArrayList<>();
        int distance = 0;
        //BFS求距离为k的点
        Queue<Integer> queue2 = new LinkedList<>();
        queue2.offer(target.val);
        while (!queue2.isEmpty()){
            int size = queue2.size();
            for (int i = 0; i < size; i++) {
                int currNode = queue2.poll();
                visit.add(currNode);    //避免死循环
                for (int neighbor : graph.get(currNode)){
                    if (!visit.contains(neighbor)){
                        //如果当前距离为k-1，则其子节点距离为k，应该加入到结果中，否则继续入队
                        if (distance == k - 1){
                            result.add(neighbor);
                        }else {
                            queue2.offer(neighbor);
                        }
                    }
                }
            }
            distance++;
        }
        return result;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}
