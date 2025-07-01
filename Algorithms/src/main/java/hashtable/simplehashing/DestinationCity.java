package hashtable.simplehashing;

import java.util.HashSet;

/*
You are given the array paths, where paths[i] = [cityAi, cityBi] means there exists a direct path going from cityAi to cityBi. Return the destination city, that is, the city without any path outgoing to another city.

It is guaranteed that the graph of paths forms a line without any loop, therefore, there will be exactly one destination city.



Example 1:

Input: paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
Output: "Sao Paulo"
Explanation: Starting at "London" city you will reach "Sao Paulo" city which is the destination city. Your trip consist of: "London" -> "New York" -> "Lima" -> "Sao Paulo".
Example 2:

Input: paths = [["B","C"],["D","B"],["C","A"]]
Output: "A"
Explanation: All possible trips are:
"D" -> "B" -> "C" -> "A".
"B" -> "C" -> "A".
"C" -> "A".
"A".
Clearly the destination city is "A".
Example 3:

Input: paths = [["A","Z"]]
Output: "Z"


Constraints:

1 <= paths.length <= 100
paths[i].length == 2
1 <= cityAi.length, cityBi.length <= 10
cityAi != cityBi
All strings consist of lowercase and uppercase English letters and the space character.
 */
public class DestinationCity {
    public static String destCity(String[][] paths) {
        // Set to store all cities that have outgoing paths
        HashSet<String> outgoing = new HashSet<>();
        // Set to store all destination cities
        HashSet<String> incoming = new HashSet<>();

        for (String[] path : paths) {
            outgoing.add(path[0]);
            incoming.add(path[1]);
        }

        // The destination city is the one that never appears as a starting city
        for (String city : incoming) {
            if (!outgoing.contains(city)) {
                return city;
            }
        }
        // Should never reach here as per problem constraints
        return "";
    }

    public static void main(String[] args) {
        // Example 1
        String[][] paths1 = {{"London","New York"},{"New York","Lima"},{"Lima","Sao Paulo"}};
        System.out.println("Output: " + destCity(paths1)); // Output: Sao Paulo

        // Example 2
        String[][] paths2 = {{"B","C"},{"D","B"},{"C","A"}};
        System.out.println("Output: " + destCity(paths2)); // Output: A

        // Example 3
        String[][] paths3 = {{"A","Z"}};
        System.out.println("Output: " + destCity(paths3)); // Output: Z
    }
}
