package recursion;
/*
Ingredients of Alchemist
You are a young alchemist on a quest to brew a magical potion. Each day, you add a specific number of ingredients to the cauldron. Write a function to recursively calculate the total number of ingredients used to brew the potion over a given number of days.

Input Format:

An integer n, representing the number of days you have brewed the potion.
An integer ingredientsPerDay, representing the number of ingredients added to the cauldron each day.
Output Format:

An integer representing the total number of ingredients used over n days.
Sample Input 1:

8 9
Sample Output 1:

72
Explanation 1:

For 8 days, adding 9 ingredients each day results in (8 * 9 = 72) ingredients.

Sample Input 2:

10 6
Sample Output 2:

60
Explanation 2:

For 10 days, adding 6 ingredients each day results in (10 * 6 = 60) ingredients.

Constraints:

(1 <= n <= 20)
(1 <= ingredientsPerDay <= 100)
Note:The function should return the result.
 */
import java.util.Scanner;

public class AlchemistIngradients {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.println(calculateTotalIngredients(n, m));
    }

    private static int calculateTotalIngredients(int days,int ingradientsPerDay){
        if(days==0){
            return 0;
        }
        return ingradientsPerDay+calculateTotalIngredients(days-1,ingradientsPerDay);
    }
}
