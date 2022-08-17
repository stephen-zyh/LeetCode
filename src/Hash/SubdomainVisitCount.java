package Hash;

import java.util.*;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 811
 * @date 2022/8/7 9:17
 */
public class SubdomainVisitCount {
    public List<String> subdomainVisits(String[] cpdomains) {
        HashMap<String, Integer> map = new HashMap<>();
        ArrayList<String> result = new ArrayList<>();
        for (String cpdomain : cpdomains) {
            //将出现次数和域名分离
            String[] item = cpdomain.split(" ");
            int count = Integer.parseInt(item[0]);
            String domain = item[1];
            //统计域名出现次数
            map.put(domain, map.getOrDefault(domain, 0) + count);
            for (int i = 0; i < domain.length(); i++) {
                if (domain.charAt(i) == '.'){
                    //统计子域名出现次数，子域名以'.'分隔
                    String subDomain = domain.substring(i + 1);
                    map.put(subDomain, map.getOrDefault(subDomain, 0) + count);
                }
            }

        }
        //将结果转存到list中
        Set<String> keySet = map.keySet();
        for(String key : keySet){
            result.add(map.get(key) + " "+ key);
        }
        return result;
    }

    public static void main(String[] args) {
//        String[] cpdomains = {"9001 discuss.leetcode.com"};
        String[] cpdomains = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        List<String> result = new SubdomainVisitCount().subdomainVisits(cpdomains);
        System.out.println(Arrays.toString(result.toArray()));
    }
}
