package strings.backtracking;

import java.util.LinkedList;
import java.util.Queue;

/*
Design the CombinationIterator class:

CombinationIterator(string characters, int combinationLength) Initializes the object with a string characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
next() Returns the next combination of length combinationLength in lexicographical order.
hasNext() Returns true if and only if there exists a next combination.


Example 1:

Input
["CombinationIterator", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
[["abc", 2], [], [], [], [], [], []]
Output
[null, "ab", true, "ac", true, "bc", false]

Explanation
CombinationIterator itr = new CombinationIterator("abc", 2);
itr.next();    // return "ab"
itr.hasNext(); // return True
itr.next();    // return "ac"
itr.hasNext(); // return True
itr.next();    // return "bc"
itr.hasNext(); // return False


Constraints:

1 <= combinationLength <= characters.length <= 15
All the characters of characters are unique.
At most 104 calls will be made to next and hasNext.
It is guaranteed that all calls of the function next are valid.
 */
public class IteratorForCombination {
        private final Queue<String> combinations;
        private final String characters;
        private final int combinationLength;

    public IteratorForCombination(String characters, int combinationLength) {
        this.characters = characters;
        this.combinationLength = combinationLength;
        this.combinations = new LinkedList<>();
        generateCombinations(0, new StringBuilder());
    }

    private void generateCombinations(int start, StringBuilder current) {
        if (current.length() == combinationLength) {
            combinations.add(current.toString());
            return;
        }

        for (int i = start; i < characters.length(); i++) {
            current.append(characters.charAt(i));
            generateCombinations(i + 1, current);
            current.deleteCharAt(current.length() - 1);
        }
    }

    public String next() {
        return combinations.poll();
    }

    public boolean hasNext() {
        return !combinations.isEmpty();
    }

    public static void main(String[] args) {
        IteratorForCombination itr = new IteratorForCombination("abc", 2);
        System.out.println(itr.next());    // Output: "ab"
        System.out.println(itr.hasNext()); // Output: true
        System.out.println(itr.next());    // Output: "ac"
        System.out.println(itr.hasNext()); // Output: true
        System.out.println(itr.next());    // Output: "bc"
        System.out.println(itr.hasNext()); // Output: false
    }
}
