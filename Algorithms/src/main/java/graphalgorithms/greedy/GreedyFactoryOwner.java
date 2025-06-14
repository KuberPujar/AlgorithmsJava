package graphalgorithms.greedy;

import java.util.Arrays;

/*
Greedy Factory Owner
You are a greedy factory owner and it is the month end and you have to distribute salary to your workers. Each worker gets a minimum wage of ₹1,000 and based on their performance he gets some extra money. All the workers are standing in a line and every worker knows the performance value of the worker just adjacent to him. They will be mad if they get less or equal amount of money compared to the worker adjacent to him if he had less performance value than them (They don't care if they had equal amount of performance).

That's why if the worker has a higher performance than their adjacent peers then they will have to get at least ₹1,000 extra. For example: performance value of three workers is given as [9, 5, 4] then you would distribute salary as [3000, 2000, 1000].

Return the minimum amount of total wage that you can give to your workers without them getting mad.

Input Format:

First Line contains a single integer 'n' denoting the number of workers.

Second line contains 'n' space separated integers denoting the performance value of the ith worker.

Output format:

Return a single integer denoting the minimum amount of total wage that you can give to your workers without them getting mad.

Sample Input:

4
5 6 6 4
Sample Output:

6000

Explanation:

You can distribute salary as follows [1000, 2000, 2000, 1000] which totals to 6000 and is the minimum answer possible.
Sample Input:

4
7 8 8 11
Sample Output:

6000

Explanation:

In this case, we have 4 workers with performance values [7, 8, 8, 11].

Minimum Salary: Each worker starts with ₹1,000.
Adjustments:
Worker 1 (7): ₹1,000 (baseline).
Worker 2 (8): ₹2,000, since they outperform Worker 1 (7).
Worker 3 (8): ₹2,000, same as Worker 2 (equal performance).
Worker 4 (11): ₹3,000, because they outperform Worker 3 (8).
Final Salaries: [1000, 2000, 2000, 3000]
Constraints:

1<=n<=10^4

1<=ar[i]<=100

Note:The function should return the result. The driver code will handle printing the output.
 */
public class GreedyFactoryOwner {
    /**
     * Calculates minimum total wage using greedy two-pass approach
     * @param performance array of performance values
     * @return minimum total wage
     */
    public static int minimumWage(int[] performance) {
        int n = performance.length;
        if (n == 0) return 0;
        if (n == 1) return 1000;

        // Initialize salary array with base wage of 1000 for all workers
        int[] salary = new int[n];
        Arrays.fill(salary, 1000);

        // Left to right pass: ensure workers with higher performance than left neighbor get more
        for (int i = 1; i < n; i++) {
            if (performance[i] > performance[i - 1]) {
                salary[i] = salary[i - 1] + 1000;
            }
        }

        // Right to left pass: ensure workers with higher performance than right neighbor get more
        for (int i = n - 2; i >= 0; i--) {
            if (performance[i] > performance[i + 1]) {
                salary[i] = Math.max(salary[i], salary[i + 1] + 1000);
            }
        }

        // Calculate total wage
        int totalWage = 0;
        for (int wage : salary) {
            totalWage += wage;
        }

        return totalWage;
    }

    /**
     * Alternative implementation with detailed explanation
     */
    public static int minimumWageDetailed(int[] performance) {
        int n = performance.length;
        if (n == 0) return 0;
        if (n == 1) return 1000;

        int[] salary = new int[n];
        Arrays.fill(salary, 1000);

        System.out.println("Initial salaries: " + Arrays.toString(salary));
        System.out.println("Performance: " + Arrays.toString(performance));

        // Left to right pass
        System.out.println("\n=== Left to Right Pass ===");
        for (int i = 1; i < n; i++) {
            if (performance[i] > performance[i - 1]) {
                salary[i] = salary[i - 1] + 1000;
                System.out.printf("Worker %d (%d) > Worker %d (%d) -> Salary[%d] = %d\n",
                        i, performance[i], i-1, performance[i-1], i, salary[i]);
            }
        }
        System.out.println("After left pass: " + Arrays.toString(salary));

        // Right to left pass
        System.out.println("\n=== Right to Left Pass ===");
        for (int i = n - 2; i >= 0; i--) {
            if (performance[i] > performance[i + 1]) {
                int newSalary = salary[i + 1] + 1000;
                if (newSalary > salary[i]) {
                    System.out.printf("Worker %d (%d) > Worker %d (%d) -> Salary[%d] = max(%d, %d) = %d\n",
                            i, performance[i], i+1, performance[i+1], i, salary[i], newSalary, newSalary);
                    salary[i] = newSalary;
                }
            }
        }
        System.out.println("After right pass: " + Arrays.toString(salary));

        int total = Arrays.stream(salary).sum();
        System.out.println("Total wage: " + total);
        return total;
    }

    /**
     * Optimized space solution using single pass with peaks and valleys
     */
    public static int minimumWageOptimized(int[] performance) {
        int n = performance.length;
        if (n == 0) return 0;
        if (n == 1) return 1000;

        int total = 1000; // First worker always gets base wage
        int up = 0, down = 0, peak = 0;

        for (int i = 1; i < n; i++) {
            if (performance[i] > performance[i - 1]) {
                up++;
                down = 0;
                peak = up;
                total += 1000 * (1 + up);
            } else if (performance[i] < performance[i - 1]) {
                down++;
                up = 0;
                total += 1000 * (1 + down);
                if (peak >= down) {
                    total -= 1000;
                }
            } else {
                up = down = peak = 0;
                total += 1000;
            }
        }

        return total;
    }

    public static void main(String[] args) {
        // Test case 1
        int[] performance1 = {5, 6, 6, 4};
        System.out.println("=== Test Case 1 ===");
        System.out.println("Performance: " + Arrays.toString(performance1));
        System.out.println("Minimum wage: " + minimumWage(performance1));
        System.out.println("Expected: 6000\n");

        // Test case 2
        int[] performance2 = {7, 8, 8, 11};
        System.out.println("=== Test Case 2 ===");
        System.out.println("Performance: " + Arrays.toString(performance2));
        System.out.println("Minimum wage: " + minimumWage(performance2));
        System.out.println("Expected: 8000\n");

        // Test case 3 - Detailed explanation
        int[] performance3 = {9, 5, 4};
        System.out.println("=== Test Case 3 (Detailed) ===");
        minimumWageDetailed(performance3);
        System.out.println();

        // Test case 4 - Edge cases
        int[] performance4 = {1, 2, 3, 4, 5};
        System.out.println("=== Test Case 4 (Ascending) ===");
        System.out.println("Performance: " + Arrays.toString(performance4));
        System.out.println("Minimum wage: " + minimumWage(performance4));
        System.out.println();

        int[] performance5 = {5, 4, 3, 2, 1};
        System.out.println("=== Test Case 5 (Descending) ===");
        System.out.println("Performance: " + Arrays.toString(performance5));
        System.out.println("Minimum wage: " + minimumWage(performance5));
        System.out.println();

        // Compare all approaches
        System.out.println("=== Comparing Approaches ===");
        System.out.println("Two-pass: " + minimumWage(performance1));
        System.out.println("Optimized: " + minimumWageOptimized(performance1));
    }

    /**
     * Driver function that matches the expected interface
     */
    public static int solve(int[] performance) {
        return minimumWage(performance);
    }
}
