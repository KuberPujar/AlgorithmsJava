package strings;

public class VotingAlgorithm {
    public static void main(String[] args) {
        String[] votes = {"A", "A", "A", "B", "B", "C", "C", "C", "C", "C"};
        System.out.println(votingAlgorithm(votes));
    }

    public static String votingAlgorithm(String[] votes) {
        String candidate = votes[0];
        int count = 1;
        for (int i = 1; i < votes.length; i++) {
            if (votes[i].equals(candidate)) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                candidate = votes[i];
                count = 1;
            }
        }
        return candidate;
    }
}
