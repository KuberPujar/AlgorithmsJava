package graphalgorithms.bfsdfs;

import java.util.*;

/*
Reconstruct Itinerary
You are tasked with developing a program that reconstructs a travel itinerary from a given list of airline tickets. Each ticket is represented by a pair of strings indicating the departure ("from") and the arrival ("to") airports of a flight. The itinerary must start from the airport code "MUM" and must be constructed such that if multiple valid itineraries exist, the one with the smallest lexical order (when read as a single string) is chosen.

Input Format:
The first line of input will contain a single integer, n, indicating the total number of strings to follow. This number will always be even, as each pair of strings represents a single flight ticket.
The next n strings represent the departure and arrival airports for n/2 tickets. Each pair of strings is a ticket with the first string being the departure airport and the second string being the arrival airport.
Output Format:
The output should be a single line containing the reconstructed itinerary, with each airport code separated by a space. The itinerary must begin with "MUM" and follow the rules described above.
Sample Input:
4
MUC LHR MUM MUC SFO SJC LHR SFO
Sample Output:
MUM MUC LHR SFO SJC

Explanation:
The sample input represents four tickets: ["MUC", "LHR"], ["MUM", "MUC"], ["SFO", "SJC"], and ["LHR", "SFO"].
Starting from "MUM", the itinerary follows to "MUC". From "MUC", the next destination with the smallest lexical order is "LHR". From "LHR", the itinerary proceeds to "SFO", and finally, from "SFO" to "SJC".
The reconstructed itinerary is "MUM MUC LHR SFO SJC", satisfying the requirement to start from "MUM" and to choose the itinerary with the smallest lexical order in case of multiple possibilities.
Sample Input:
2
MUM SFO
SFO TJI
Sample Output:
MUM SFO TJI

Explanation:
This input represents two tickets:

Ticket 1: MUM (departure) to SFO (arrival)
Ticket 2: LUC (departure) to MUM (arrival)
Given these tickets, the goal is to reconstruct an itinerary that starts from "MUM".

Starting from "MUM", the only available flight is to "SFO".
After arriving at "SFO", there are no further tickets available that allow us to continue the itinerary, as there is no ticket departing from "SFO".
Since this is the only valid itinerary starting from "MUM", it meets the requirements and is returned as the output. There are no other options to consider or paths to follow, making this a straightforward reconstruction.
Constraints:
1 <= Number of tickets <= 2 * 10^4
Note:The function should return the result. The driver code will handle printing the output.
 */
public class ReconstructItenary {
    public static List<String> reconstructItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            String u = ticket.get(0);
            String v = ticket.get(1);
            graph.computeIfAbsent(u, k -> new PriorityQueue<>()).add(v);
        }

        LinkedList<String> itinerary = new LinkedList<>();
        dfs("MUM", graph, itinerary);
        return Collections.singletonList(String.join(" ", itinerary));
    }

    private static void dfs(String node, Map<String, PriorityQueue<String>> graph, LinkedList<String> itinerary) {
        PriorityQueue<String> pq = graph.get(node);
        if (pq != null) {
            while (!pq.isEmpty()) {
                String next = pq.poll();
                dfs(next, graph, itinerary);
            }
        }
        itinerary.addFirst(node);
    }

    public static void main(String[] args) {
        List<List<String>> tickets = Arrays.asList(
                Arrays.asList("MUC", "LHR"),
                Arrays.asList("MUM", "MUC"),
                Arrays.asList("SFO", "SJC"),
                Arrays.asList("LHR", "SFO")
        );

        String result = String.valueOf(reconstructItinerary(tickets));
        System.out.println(result); // Output: MUM MUC LHR SFO SJC
    }
}
