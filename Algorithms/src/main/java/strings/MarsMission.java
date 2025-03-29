package strings;

import java.util.Scanner;

public class MarsMission{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String s = scanner.next();

        String result = "";
        int i = 0;

        while (i < n) {
            if (i + 1 >= n) {
                break;
            }

            char consonant = s.charAt(i);
            char vowel = s.charAt(i + 1);
            String syllable = "" + consonant + vowel;

            if (i + 2 < n) {
                char nextChar = s.charAt(i + 2);
                if (!isVowel(nextChar)) {
                    if (i + 3 >= n) {
                        syllable += nextChar;
                        i += 3;
                    } else {
                        char nextNextChar = s.charAt(i + 3);
                        if (!isVowel(nextNextChar)) {
                            syllable += nextChar;
                            i += 3;
                        } else {
                            i += 2;
                        }
                    }
                } else {
                    i += 2;
                }
            } else {
                i += 2;
            }

            if (result.isEmpty()) {
                result = syllable;
            } else {
                result += " " + syllable;
            }
        }

        System.out.println(result);
    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
        }