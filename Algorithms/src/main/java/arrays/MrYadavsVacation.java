package arrays;

import java.util.Scanner;

public class MrYadavsVacation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int t = sc.nextInt();
        int[] weather = new int[n];
        for (int i = 0; i < n; i++) {
            weather[i] = sc.nextInt();
        }
        sc.close();

        System.out.println(countVacationWays(n, k, t, weather));
    }

    public static int countVacationWays(int n, int k, int t, int[] weather) {
        int count = 0;
        for (int i = 0; i <= n - k; i++) {
            boolean valid = true;
            for (int j = i; j < i + k; j++) {
                if (weather[j] > t) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                count++;
                for (int j = i + k; j < n && weather[j] <= t; j++) {
                    count++;
                }
            }
        }
        return count;
    }
}