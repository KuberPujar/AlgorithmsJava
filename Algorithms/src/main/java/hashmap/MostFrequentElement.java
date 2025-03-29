package hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MostFrequentElement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of elements in the array: ");
        int n = sc.nextInt();
        int[] a = new int[n];
        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        sc.close(); // Close the scanner to prevent resource leak
        int mostFrequent = findMostFrequent(a);
        System.out.println("Most frequent element is: " + mostFrequent);
    }

    private static int findMostFrequent(int[] arr){
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for(int n:arr){
            frequencyMap.put(n,frequencyMap.getOrDefault(n,0)+1);
        }

        int mostFrequent = arr[0];
        int maxCount = frequencyMap.get(mostFrequent);
        for(Map.Entry<Integer,Integer> entry:frequencyMap.entrySet()){
            int  currentCount= entry.getValue();
            int currentElement = entry.getKey();
            if(currentCount>maxCount){
                maxCount=currentCount;
                mostFrequent = currentElement;
            }
            else if(currentCount==maxCount){
                mostFrequent = Math.min(mostFrequent, currentElement);
            }
        }
        return mostFrequent;
    }
}
