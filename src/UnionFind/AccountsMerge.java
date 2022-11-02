package UnionFind;

import java.util.*;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 721
 * @date 2022/11/1 16:14
 */
public class AccountsMerge {
    Map<String, String> parent = new HashMap<>();   //节点与其父节点的映射
    Map<String, TreeSet<String>> union = new HashMap<>();   //根和与根处于同一个图的节点集合的映射，TreeSet会将节点按照ASCII排序
    Map<String, String> emailToName = new HashMap<>();  //邮箱到名字的映射
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        for(List<String> account : accounts){
            int size = account.size();
            //初始化，让每个节点自己指向自己
            for (int i = 1; i < size; i++) {
                parent.put(account.get(i), account.get(i));
            }
            //emailToName在建立映射时，取相同列表中第一个邮箱与名字映射即可，相同列表中邮箱在parent中映射到的值相同
            emailToName.put(account.get(1), account.get(0));
        }

        for(List<String> account : accounts){
            //获取当前列表第一个邮箱的parent
            String p = findParent(account.get(1), parent);
            int size = account.size();
            for (int i = 2; i < size; i++) {
                String pi = findParent(account.get(i), parent);
                parent.put(pi, p);  //使得当前列表中邮箱在parent中映射到的值相同，都等于列表第一个邮箱的parent
            }
        }

        for(List<String> account : accounts){
            String p = findParent(account.get(1), parent);
            //对于每一个根新建一个TreeSet，TreeSet会将添加的节点按照ASCII排序
            if (!union.containsKey(p)){
                union.put(p, new TreeSet<>());
            }
            int size = account.size();
            for (int i = 1; i < size; i++) {
                union.get(p).add(account.get(i));
            }
        }

        List<List<String>> result = new ArrayList<>();
        for (String p : union.keySet()){
            //对于每一个根新建一个list，将根为p的节点都添加到其中，最后将根对应的名字添加到list首部
            ArrayList<String> email = new ArrayList<>(union.get(p));
            email.add(0, emailToName.get(p));
            result.add(email);
        }
        return result;
    }

    //寻找节点p的根，根的特征是自己指向自己
    public String findParent(String p, Map<String, String> parent){
        while (!parent.get(p).equals(p)){
            p = parent.get(p);
        }
        return p;
    }

    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(new ArrayList<>(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com")));
        accounts.add(new ArrayList<>(Arrays.asList("John", "johnnybravo@mail.com")));
        accounts.add(new ArrayList<>(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com")));
        accounts.add(new ArrayList<>(Arrays.asList("Mary", "mary@mail.com")));
        List<List<String>> result = new AccountsMerge().accountsMerge(accounts);
        System.out.println(result);
    }
}
