package arrays;

import java.util.Arrays;
import java.util.Scanner;

            public class FindXORConnects {
                public static void main(String[] args) {
                    Scanner sc = new Scanner(System.in);
                    int n = sc.nextInt();
                    int m = sc.nextInt();
                    int[] A = new int[n];
                    int[] B = new int[m];
                    for (int i = 0; i < n; i++) {
                        A[i] = sc.nextInt();
                    }
                    for (int i = 0; i < m; i++) {
                        B[i] = sc.nextInt();
                    }
                    sc.close();

                    System.out.println(findXORConnects(A, B));
                }

                public static int findXORConnects(int[] A, int[] B) {
                    Arrays.sort(A);
                    Arrays.sort(B);

                    int i = 0, j = 0, connects = 0;
                    while (i < A.length && j < B.length) {
                        if (A[i] == B[j]) {
                            connects++;
                            i++;
                            j++;
                        } else if (A[i] < B[j]) {
                            i++;
                        } else {
                            j++;
                        }
                    }

                    return connects;
                }
            }