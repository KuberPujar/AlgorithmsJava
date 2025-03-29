package sets;

import java.util.HashSet;
import java.util.Set;

public class SetsBasics {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>(Set.of(1, 2, 3, 4, 5));
        Set<Integer> set1= Set.of(1, 2, 4, 5,6);
        //union
        set.addAll(set1);
        System.out.println("Union");
        set.forEach(System.out::print);
        System.out.println();
        //intersection
        set.retainAll(set1);
        System.out.println("Intersection");
        set.forEach(System.out::print);
        System.out.println();
        //difference
        set.removeAll(set1);
        System.out.println("Difference");
        set.forEach(System.out::print);
    }
}
