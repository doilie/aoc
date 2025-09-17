package aoc2016.day6;

public class Signal {
    private final String[] inputLines;
    private final int messageLength;

    public Signal(String[] inputLines) {
        this.inputLines = inputLines;
        this.messageLength = inputLines[0].length() - 1;
    }

    public Signal(String input) {
        this(input.split("\n"));
    }

    public String decodeMessage(boolean useLeastCommon) {
        StringBuilder message = new StringBuilder();

        for (int i = 0; i < messageLength; i++) {
            int[] charCounts = new int[26]; // Assuming only lowercase a-z

            for (String line : inputLines) {
                char c = line.charAt(i);
                charCounts[c - 'a']++;
            }

            int targetIndex = useLeastCommon ? findLeastCommonIndex(charCounts) : findMostCommonIndex(charCounts);
            message.append((char) (targetIndex + 'a'));
        }

        return message.toString();
    }

    private int findMostCommonIndex(int[] counts) {
        int maxIndex = 0;
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] > counts[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private int findLeastCommonIndex(int[] counts) {
        int minIndex = 0;
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] < counts[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }
}
