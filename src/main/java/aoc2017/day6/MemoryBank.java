package aoc2017.day6;

public class MemoryBank {
    private int[] banks;
    private int size;

    public MemoryBank(String input) {
        String[] parts = input.trim().split("\\s+");
        size = parts.length;
        banks = new int[size];
        for (int i = 0; i < size; i++) {
            banks[i] = Integer.parseInt(parts[i]);
        }
    }
    public String getState() {
        StringBuilder sb = new StringBuilder();
        for (int b : banks) {
            sb.append(b).append(",");
        }
        return sb.toString();
    }
    public void redistribute(int index) {
        int blocks = banks[index];
        banks[index] = 0;
        int i = (index + 1) % size;
        while (blocks > 0) {
            banks[i]++;
            blocks--;
            i = (i + 1) % size;
        }
    }
    public int findMaxIndex() {
        int maxIndex = 0;
        for (int i = 1; i < size; i++) {
            if (banks[i] > banks[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
