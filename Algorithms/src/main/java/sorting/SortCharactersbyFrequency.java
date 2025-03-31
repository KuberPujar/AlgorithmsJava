package sorting;

import java.util.*;

public class SortCharactersbyFrequency {
    public static void main(String[] args) {
        String s = "Mississippi";
        System.out.println(frequencySort(s));
    }

    private static String frequencySort(String s){
        Map<Character,Integer> frequencyMap=new HashMap<>();
        for(char c:s.toCharArray()){
            frequencyMap.put(c,frequencyMap.getOrDefault(c,0)+1);
        }

        List<Character> characters=new ArrayList<>(frequencyMap.keySet());
        Collections.sort(characters,(a,b)->{
            int frequenceCompare=frequencyMap.get(b).compareTo(frequencyMap.get(a));
            if(frequenceCompare!=0){
                return frequenceCompare;
            }
            else {
                return a.compareTo(b);
            }
        });

        StringBuilder sb=new StringBuilder();
        for(char c:characters){
            int frequency=frequencyMap.get(c);
            for(int i=0;i<frequency;i++){
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
