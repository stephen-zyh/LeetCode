package Graph_DFS;

import java.util.*;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 332
 * @date 2022/9/9 9:21
 */
public class ReconstructItinerary {
    Map<String, PriorityQueue<String>> map = new HashMap<>();
    List<String> itinerary = new ArrayList<>();
    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String src = ticket.get(0);
            String dst = ticket.get(1);
            //为每一个src建立一个堆，并按字典序排列
            if (!map.containsKey(src)){
                PriorityQueue<String> queue = new PriorityQueue<>();
                map.put(src, queue);
            }
            map.get(src).offer(dst);
        }
        //从"JFK"开始进行DFS
        sortTickets("JFK");
        return itinerary;
    }

    private void sortTickets(String ticket){
        PriorityQueue<String> queue = map.get(ticket);
        while (queue != null && !queue.isEmpty()){
            sortTickets(queue.poll());
        }
        itinerary.add(0, ticket);
    }

    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(new ArrayList<>(Arrays.asList("MUC", "LHR")));
        tickets.add(new ArrayList<>(Arrays.asList("JFK", "MUC")));
        tickets.add(new ArrayList<>(Arrays.asList("SFO", "SJC")));
        tickets.add(new ArrayList<>(Arrays.asList("LHR", "SFO")));
        List<String> itinerary = new ReconstructItinerary().findItinerary(tickets);
        System.out.println(itinerary);
    }
}
