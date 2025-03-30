package sorting;

class CharacterFrequency{
    private char character;
    private int frequency;

    public CharacterFrequency(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public char getCharacter() {
        return character;
    }

    public int getFrequency() {
        return frequency;
    }
}

public class SortCharactersbyFrequency {
    public static void main(String[] args) {
        String s = "tree";
        System.out.println(frequencySort(s));
    }

    private static String frequencySort(String s){
        int[] frequency=new int[128];
        for(char c:s.toCharArray()){
            frequency[c]++;
        }

        CharacterFrequency[] charFreqs = new CharacterFrequency[128];
        return null;
    }
}
