package graphs.euleriancircuit;

import java.util.*;

/*
You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.

All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.

For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.



Example 1:


Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
Output: ["JFK","MUC","LHR","SFO","SJC"]
Example 2:


Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.


Constraints:

1 <= tickets.length <= 300
tickets[i].length == 2
fromi.length == 3
toi.length == 3
fromi and toi consist of uppercase English letters.
fromi != toi
 */
public class ReconstructItinerary {
    //Time Complexity: O(E log E) where E is number of tickets (due to priority queue operations)
    //Space Complexity: O(E) for storing the graph
    //Java solution to reconstruct the flight itinerary using a Eulerian path approach with depth-first search (DFS)
    public List<String> findItinerary(List<List<String>> tickets) {
        // Build the graph in lexical order
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            graph.putIfAbsent(from, new PriorityQueue<>());
            graph.get(from).offer(to);
        }

        LinkedList<String> itinerary = new LinkedList<>();
        dfs("JFK", graph, itinerary);

        return itinerary;
    }

    private void dfs(String airport, Map<String, PriorityQueue<String>> graph, LinkedList<String> itinerary) {
        PriorityQueue<String> destinations = graph.get(airport);

        // Visit all destinations in lexical order
        while (destinations != null && !destinations.isEmpty()) {
            dfs(destinations.poll(), graph, itinerary);
        }

        // Add current airport to the front of the itinerary
        itinerary.addFirst(airport);
    }

    //using an explicit stack instead of recursion, dfs approach
    public List<String> findItinerary1(List<List<String>> tickets) {
        // Build the graph
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            graph.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
        }

        LinkedList<String> itinerary = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        stack.push("JFK");

        while (!stack.isEmpty()) {
            String airport = stack.peek();
            if (graph.containsKey(airport) && !graph.get(airport).isEmpty()) {
                stack.push(graph.get(airport).poll());
            } else {
                itinerary.addFirst(stack.pop());
            }
        }

        return itinerary;
    }

    public static void main(String[] args) {
        List<List<String>> lists=List.of(List.of("JFK","SFO"),List.of("JFK","ATL"),List.of("SFO","ATL"),List.of("ATL","JFK"),List.of("ATL","SFO"));
        ReconstructItinerary reconstructItinerary=new ReconstructItinerary();
        List<String> itinerary = reconstructItinerary.findItinerary(lists);
        System.out.println(itinerary);//Output: ["JFK","MUC","LHR","SFO","SJC"]

        List<List<String>> lists1=List.of(List.of("MUC","LHR"),List.of("JFK","MUC"),List.of("SFO","SJC"),List.of("LHR","SFO"));
        List<String> itinerary1 = reconstructItinerary.findItinerary(lists1);
        System.out.println(itinerary1);//Output: ["JFK","MUC","LHR","SFO","SJC"]
    }
}
