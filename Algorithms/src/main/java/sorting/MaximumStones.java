package sorting;

        import java.util.Scanner;

        public class MaximumStones {
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
                maximumStones(n, a);
            }

            private static void maximumStones(int n, int[] stones) {
                int vivekSum = 0;
                sort(stones);
                int eachShare = n/3;
                for (int i = 1; i < n-eachShare; i += 2) {
                    vivekSum += stones[i];
                }
                System.out.println(vivekSum);
            }

            private static void sort(int[] arr) {
                int n = arr.length;
                for (int i = 1; i < n; i++) {
                    int key = arr[i];
                    int j = i - 1;
                    while (j >= 0 && arr[j] < key) {
                        arr[j + 1] = arr[j];
                        j--;
                    }
                    arr[j + 1] = key;
                }
            }
        }