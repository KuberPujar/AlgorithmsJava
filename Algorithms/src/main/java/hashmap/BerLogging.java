package hashmap;

import java.util.*;

class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}

public class BerLogging {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        List<Pair<String, Integer>> input = new ArrayList<>();
        for (int j = 0; j < N; j++) {
            String name = scanner.next();
            int score = scanner.nextInt();
            input.add(new Pair<>(name, score));
        }
        String winner = findWinner(input, N);
        System.out.println(winner);
        scanner.close(); // Close the scanner to prevent resource leak


    }

    private static String findWinner(List<Pair<String, Integer>> input, int N) {
        Map<String, Integer> scores = new HashMap<>();
        Map<String, Map<Integer, Integer>> firstAchieved = new HashMap<>();

        for (int round = 0; round < N; round++) {
            Pair<String, Integer> roundData = input.get(round);
            String name = roundData.getKey();
            int score = roundData.getValue();

            // Update the player's score
            int currentScore = scores.getOrDefault(name, 0) + score;
            scores.put(name, currentScore);

            // Track the first round this score was achieved
            if (!firstAchieved.containsKey(name)) {
                firstAchieved.put(name, new HashMap<>());
            }
            Map<Integer, Integer> playerAchievements = firstAchieved.get(name);
            if (!playerAchievements.containsKey(currentScore)) {
                playerAchievements.put(currentScore, round + 1); // rounds are 1-based
            }
        }

        // Find the maximum score
        int maxScore = Integer.MIN_VALUE;
        for (int score : scores.values()) {
            if (score > maxScore) {
                maxScore = score;
            }
        }

        // Collect all players with the maximum score
        List<String> candidates = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            if (entry.getValue() == maxScore) {
                candidates.add(entry.getKey());
            }
        }

        String winner = candidates.get(0);
        if (candidates.size() > 1) {
            // Need to find who reached maxScore first
            int earliestRound = Integer.MAX_VALUE;
            for (String candidate : candidates) {
                int round = firstAchieved.get(candidate).get(maxScore);
                if (round < earliestRound) {
                    earliestRound = round;
                    winner = candidate;
                }
            }
        }

        return winner;
    }
}
