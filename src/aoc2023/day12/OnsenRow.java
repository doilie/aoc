package aoc2023.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OnsenRow {
    private static final String DAMAGED = "#";
    private static final String UNKNOWN = Character.toString('?');
    private static final String SPACE = ".";

    private final String value;
    private final List<Integer> damagedGroupsSize = new ArrayList<>();
    private final List<Integer> unknownIndices = new ArrayList<>();
    private final int damagedToFind;
    private final List<String> possibleArrangements = new ArrayList<>();

    public OnsenRow(String line, int foldSize) {
        String[] lineParts = line.split("\\s+");
        if (lineParts.length == 2) {
            StringBuilder valueFolded = new StringBuilder();
            for (int i = 0; i < foldSize; i++) {
                if (i != 0) {
                    valueFolded.append(UNKNOWN);
                }
                valueFolded.append(lineParts[0]);
                damagedGroupsSize.addAll(Arrays.stream(lineParts[1].split(",")).map(Integer::parseInt).toList());
            }
            value = valueFolded.toString();
            int totalDamaged = damagedGroupsSize.stream().mapToInt(d -> d).sum();
            int bound = value.length();
            int knownDamaged = 0;
            for (int i = 0; i < bound; i++) {
                if (Character.toString(value.charAt(i)).equals(UNKNOWN)) {
                    unknownIndices.add(i);
                }
                else if (Character.toString(value.charAt(i)).equals(DAMAGED)) {
                    knownDamaged++;
                }
            }
            damagedToFind = totalDamaged - knownDamaged;
        }
        else {
            value = "";
            damagedToFind = 0;
        }
    }

    public List<String> getPossibleArrangements() {
        possibleArrangements.clear();
        List<String> valueAsList = Arrays.stream(value.split("")).toList();
        formPossibleArrangements(valueAsList, 0, damagedToFind);
//        System.out.println(value + " " + damagedGroupsSize + ": " + possibleArrangements.size());
        return possibleArrangements;
    }

    private void formPossibleArrangements(List<String> currString, int start, int damagedLeftToFind) {
        if (unknownIndices.size() == damagedToFind) {
            possibleArrangements.add(value.replaceAll("\\".concat(UNKNOWN), DAMAGED));
        }
        else {
            if (damagedLeftToFind == 0) {
                String possibleArrangement = String.join("", currString).replaceAll("\\".concat(UNKNOWN), SPACE);
                if (isPossibleArrangement(possibleArrangement)) {
                    possibleArrangements.add(possibleArrangement);
                }
            }
            else {
                for (int i = start; i <= unknownIndices.size() - damagedLeftToFind; i++) {
                    List<String> newString = new ArrayList<>(currString);
                    newString.set(unknownIndices.get(i), DAMAGED);
                    formPossibleArrangements(newString, i + 1, damagedLeftToFind - 1);
                }
            }
        }
    }

    private boolean isPossibleArrangement(String candidate) {
        List<String> parts = Arrays.stream(candidate.split("\\.+")).filter(p -> !p.isBlank()).toList();
        if (parts.size() != damagedGroupsSize.size()) {
            return false;
        }
        for (int i = 0; i < damagedGroupsSize.size(); i++) {
            if (damagedGroupsSize.get(i) != parts.get(i).length()) {
                return false;
            }
        }
        return true;
    }
}
