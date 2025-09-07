package aoc2023.day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scratchcard {
    private final List<Integer> winningNumbers = new ArrayList<>();
    private final List<Integer> yourNumbers = new ArrayList<>();
    private int id;

    public Scratchcard(String line) {
        String[] lineParts = line.split(":");
        if (lineParts.length == 2) {
            // id
            String[] idParts = lineParts[0].split("\\s+");
            if (idParts.length == 2) {
                id = Integer.parseInt(idParts[1]);
            }
            // numbers
            String[] numberParts = lineParts[1].split("\\|");
            if (numberParts.length == 2) {
                // winning numbers
                winningNumbers.addAll(Arrays.stream(numberParts[0].trim().split("\\s+")).map(Integer::parseInt).toList());
                yourNumbers.addAll(Arrays.stream(numberParts[1].trim().split("\\s+")).map(Integer::parseInt).toList());
            }
        }
    }

    public int getPoints() {
        int matches = getMatches();
        return matches > 0 ? (int) Math.pow(2d, (double) matches - 1) : 0;
    }

    public int getMatches() {
        int matches = 0;
        for (int winningNumber : winningNumbers) {
            if (yourNumbers.contains(winningNumber)) {
                matches++;
            }
        }
        return matches;
    }

    public int getId() {
        return id;
    }
}
