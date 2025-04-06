package arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KidsWithGreatestCandies {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of kids: ");
        int numKids = scanner.nextInt();
        int[] candies = new int[numKids];
        System.out.print("Enter the number of candies each kid has: ");
        for (int i = 0; i < numKids; i++) {
            candies[i] = scanner.nextInt();
        }
        System.out.print("Enter the number of extra candies: ");
        int extraCandies = scanner.nextInt();
        List<Boolean> result = kidsWithGreatestCandies(candies, extraCandies);

        for (boolean canHaveGreatest : result) {
            System.out.print(canHaveGreatest + " ");
        }
    }

    private static List<Boolean> kidsWithGreatestCandies(int[] candies,int extraCandies){
        int maxCandies=0;
        for(int candy:candies){
            if(candy>maxCandies){
                maxCandies=candy;
            }
        }

        List<Boolean> result=new ArrayList<>();
        for (int candy : candies) {
            result.add(candy + extraCandies >= maxCandies);
        }
        return  result;
    }
}
