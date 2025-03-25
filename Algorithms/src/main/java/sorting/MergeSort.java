package sorting;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Scanner;

        public class MergeSort {
            public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter the number of elements in the list: ");
                int n = sc.nextInt();
                List<Integer> list = new ArrayList<>();
                System.out.println("Enter the elements of the list: ");
                for (int i = 0; i < n; i++) {
                    list.add(sc.nextInt());
                }
                sc.close(); // Close the scanner to prevent resource leak
                mergeSort(list, 0, n - 1);
                printList(list);
            }

            private static void merge(List<Integer> list, int l, int m, int r) {
                int n1 = m - l + 1;
                int n2 = r - m;
                List<Integer> L = new ArrayList<>(n1);
                List<Integer> R = new ArrayList<>(n2);
                for (int i = 0; i < n1; i++) {
                    L.add(list.get(l + i));
                }
                for (int i = 0; i < n2; i++) {
                    R.add(list.get(m + 1 + i));
                }

                int i = 0, j = 0;
                int k = l;
                while (i < n1 && j < n2) {
                    if (L.get(i) <= R.get(j)) {
                        list.set(k, L.get(i));
                        i++;
                    } else {
                        list.set(k, R.get(j));
                        j++;
                    }
                    k++;
                }

                while (i < n1) {
                    list.set(k, L.get(i));
                    i++;
                    k++;
                }
                while (j < n2) {
                    list.set(k, R.get(j));
                    j++;
                    k++;
                }
            }

            private static void mergeSort(List<Integer> list, int l, int r) {
                if (l < r) {
                    int m = l + (r - l) / 2;
                    mergeSort(list, l, m);
                    mergeSort(list, m + 1, r);
                    merge(list, l, m, r);
                }
            }

            private static void printList(List<Integer> list) {
                for (int i : list) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }
        }