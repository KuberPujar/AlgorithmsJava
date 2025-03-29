package searching;

import java.util.Scanner;

        public class MaximumSweetness {
            public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);
                int n = sc.nextInt();
                int[] price = new int[n];
                for (int i = 0; i < n; i++) {
                    price[i] = sc.nextInt();
                }
                int k = sc.nextInt();
                sc.close();

                System.out.println(maximumSweetness(price.length, price, k));
            }

            public static int maximumSweetness(int n, int[] price, int k) {
                mergeSort(price, 0, n - 1);

                int left = 0;
                int right = price[price.length - 1] - price[0];
                int result = 0;

                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    if (canFormJar(price, k, mid)) {
                        result = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }

                return result;
            }

            private static boolean canFormJar(int[] price, int k, int minDiff) {
                int count = 1;
                int lastPrice = price[0];

                for (int i = 1; i < price.length; i++) {
                    if (price[i] - lastPrice >= minDiff) {
                        count++;
                        lastPrice = price[i];
                        if (count == k) {
                            return true;
                        }
                    }
                }

                return false;
            }

            private static void merge(int[] price, int l, int m, int r) {
                int n1 = m - l + 1;
                int n2 = r - m;
                int[] L = new int[n1];
                int[] R = new int[n2];
                for (int i = 0; i < n1; i++) {
                    L[i] = price[l + i];
                }
                for (int i = 0; i < n2; i++) {
                    R[i] = price[m + 1 + i];
                }

                int i = 0, j = 0;
                int k = l;
                while (i < n1 && j < n2) {
                    if (L[i] <= R[j]) {
                        price[k] = L[i];
                        i++;
                    } else {
                        price[k] = R[j];
                        j++;
                    }
                    k++;
                }

                while (i < n1) {
                    price[k] = L[i];
                    i++;
                    k++;
                }
                while (j < n2) {
                    price[k] = R[j];
                    j++;
                    k++;
                }
            }

            private static void mergeSort(int[] a, int l, int r) {
                if (l < r) {
                    int m = l + (r - l) / 2;
                    mergeSort(a, l, m);
                    mergeSort(a, m + 1, r);
                    merge(a, l, m, r);
                }
            }
        }