package queues;
/*
Tour of all Petrol Pump
Suppose there is a circle with N petrol pumps. You will be given two sets of data:

The amount of petrol that each petrol pump has.
The distance from that petrol pump to the next petrol pump.
Find a starting point where the truck can start to get through the complete circle without exhausting its petrol in between. If no such path exists, return -1.

Note: Assume for 1 liter of petrol, the truck can go 1 unit of distance.

Input Format:

The first line contains an integer 'N' (the number of petrol pumps).
The second line contains 'N' space-separated integers representing the amount of petrol at each petrol pump.
The third line contains 'N' space-separated integers representing the distance from each petrol pump to the next petrol pump.
Output Format:

Return the index of the starting petrol pump if a circular tour is possible, otherwise return -1.
Sample Input 1:

4
4 6 7 4
6 5 3 5
Sample Output 1:

1
Explanation:

There are 4 petrol pumps with the amount of petrol and distance to the next petrol pump as follows:

{4, 6}
{6, 5}
{7, 3}
{4, 5}
The first point from where the truck can make a circular tour is the 2nd petrol pump. Therefore, the output is 1 (index of the 2nd petrol pump).

Sample Input 2:

3
6 3 7
4 6 3
Sample Output 2:

2
Explanation:

There are 3 petrol pumps with the amount of petrol and distance to the next petrol pump as follows:

{6, 4}
{3, 6}
{7, 3}
The first point from where the truck can make a circular tour is the 3rd petrol pump. Therefore, the output is 2 (index of the 3rd petrol pump).

Constraints:

2 ≤ N ≤ 10000
1 ≤ petrol, distance ≤ 1000
Note: The function should return the result.
 */
import java.util.Scanner;

class PetrolPump {
    int petrol;
    int distance;

    PetrolPump(int petrol, int distance) {
        this.petrol = petrol;
        this.distance = distance;
    }
}

public class PetrolPumpTour {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input
        int N = scanner.nextInt();
        PetrolPump[] petrol = new PetrolPump[N];

        for (int i = 0; i < N; i++) {
            int petrolAmount = scanner.nextInt();
            petrol[i] =new PetrolPump(petrolAmount, 0);
        }
        for (int i = 0; i < N; i++) {
            petrol[i].distance = scanner.nextInt();;
        }


        // Find starting point
        int start = findStartingPetrolPump(petrol, N);
        System.out.println(start);
    }

    public static int findStartingPetrolPump(PetrolPump[] petrol,int n) {
        int start = 0;
        int end = 1;
        int currentPetrol = petrol[start].petrol - petrol[start].distance;

        // If currentPetrol becomes negative, we can't start from current 'start'
        while (end != start || currentPetrol < 0) {
            // If currentPetrol becomes negative, move start forward
            while (currentPetrol < 0 && start != end) {
                currentPetrol -= petrol[start].petrol - petrol[start].distance;
                start = (start + 1) % n;

                // If we've come back to the original start, no solution exists
                if (start == 0) {
                    return -1;
                }
            }

            // Add next petrol pump to current tour
            currentPetrol += petrol[end].petrol - petrol[end].distance;
            end = (end + 1) % n;
        }

        return start;
    }
}