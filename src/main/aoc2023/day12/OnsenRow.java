package aoc2023.day12;

import java.util.*;

public class OnsenRow {
    private static final String DAMAGED = "#";
    private static final String UNKNOWN = Character.toString('?');
    private static final String SPACE = ".";

    private final String value;
    private final List<Integer> damagedGroupsSize = new ArrayList<>();
    private final List<Integer> unknownIndices = new ArrayList<>();
    private int damagedToFind;
    private final List<String> possibleArrangements = new ArrayList<>();
    private final Set<String> possibleArrangementsFolded = new HashSet<>();

    public OnsenRow(String line) {
        String[] lineParts = line.split("\\s+");
        if (lineParts.length == 2) {
            value = lineParts[0];
            damagedGroupsSize.addAll(Arrays.stream(lineParts[1].split(",")).map(Integer::parseInt).toList());
            initialize();
        }
        else {
            value = "";
            damagedToFind = 0;
        }
    }

    public OnsenRow(String value, List<Integer> damagedGroupsSize) {
        this.value = value;
        this.damagedGroupsSize.addAll(damagedGroupsSize);
        initialize();
    }

    private void initialize() {
        int totalDamaged = this.damagedGroupsSize.stream().mapToInt(d -> d).sum();
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
        return isPossibleArrangement(candidate, damagedGroupsSize);
    }

    private static boolean isPossibleArrangement(String candidate, List<Integer> damagedGroups) {
        List<String> parts = Arrays.stream(candidate.split("\\.+")).filter(p -> !p.isBlank()).toList();
        if (parts.size() != damagedGroups.size()) {
            return false;
        }
        for (int i = 0; i < damagedGroups.size(); i++) {
            if (damagedGroups.get(i) != parts.get(i).length()) {
                return false;
            }
        }
        return true;
    }

    public long getPossibleArrangementsFoldedCount() {
        possibleArrangementsFolded.clear();

        OnsenRow unknownStart = new OnsenRow(getValueWithUnknownStart(), damagedGroupsSize);
        OnsenRow unknownEnd = new OnsenRow(getValueWithUnknownEnd(), damagedGroupsSize);

        List<String> possibleArrangements = this.getPossibleArrangements();
        List<String> possibleArrangementsStart = unknownStart.getPossibleArrangements();
        List<String> possibleArrangementsEnd = unknownEnd.getPossibleArrangements();
        List<String> tempList = new ArrayList<>();
        List<Integer> damagedGroups = new ArrayList<>(damagedGroupsSize);
        damagedGroups.addAll(damagedGroupsSize);

        long count1 = 0;
        for (String s1 : possibleArrangements) {
            String str1 = s1.concat(possibleArrangementsStart.get(0));
            if (isPossibleArrangement(str1, damagedGroups)) {
                tempList.add(str1);
                count1 += (long) possibleArrangementsStart.size() * possibleArrangementsStart.size() * possibleArrangementsStart.size() * possibleArrangementsStart.size();
            }
        }

        long count2 = 0;
        for (String s1 : possibleArrangementsEnd) {
            String str1 = s1.concat(possibleArrangementsEnd.get(0));
//            List<String> duplicates = tempList.stream().filter(s -> s.startsWith(str1)).toList();
            if (isPossibleArrangement(str1, damagedGroups)) {
                count2 += (long) possibleArrangementsEnd.size() * possibleArrangementsEnd.size() * possibleArrangementsEnd.size() * possibleArrangements.size();
            }
        }

        System.out.println(value + " " + damagedGroupsSize + ": " + Math.max(count1, count2));

        return Math.max(count1, count2);
    }


    public Set<String> getPossibleArrangementsFolded() {
        possibleArrangementsFolded.clear();

        OnsenRow unknownStart = new OnsenRow(getValueWithUnknownStart(), damagedGroupsSize);
        OnsenRow unknownEnd = new OnsenRow(getValueWithUnknownEnd(), damagedGroupsSize);

        List<String> possibleArrangements = this.getPossibleArrangements();
        List<String> possibleArrangementsStart = unknownStart.getPossibleArrangements();
        List<String> possibleArrangementsEnd = unknownEnd.getPossibleArrangements();

        for (String s1 : possibleArrangementsEnd) {
            String str1 = s1.concat(possibleArrangementsEnd.get(0));
            if (splitToParts(str1).size() == this.damagedGroupsSize.size() * 2) {
                for (String s2 : possibleArrangementsEnd) {
                    String str2 = s1.concat(s2.concat(possibleArrangementsEnd.get(0)));
                    if (splitToParts(str2).size() == this.damagedGroupsSize.size() * 3) {
                        for (String s3 : possibleArrangementsEnd) {
                            String str3 = s1.concat(s2.concat(s3.concat(possibleArrangementsEnd.get(0))));
                            if (splitToParts(str3).size() == this.damagedGroupsSize.size() * 4) {
                                for (String s4 : possibleArrangementsEnd) {
                                    String str4 = s1.concat(s2.concat(s3.concat(s4.concat(possibleArrangementsEnd.get(0)))));
                                    if (splitToParts(str4).size() == this.damagedGroupsSize.size() * 5) {
                                        possibleArrangementsFolded.addAll(possibleArrangements.stream().map(s5 -> s1.concat(s2.concat(s3.concat(s4.concat(s5))))).toList());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        for (String s1 : possibleArrangements) {
            String str1 = s1.concat(possibleArrangementsStart.get(0));
            if (splitToParts(str1).size() == this.damagedGroupsSize.size() * 2) {
                for (String s2 : possibleArrangementsStart) {
                    String str2 = s1.concat(s2.concat(possibleArrangementsStart.get(0)));
                    if (splitToParts(str2).size() == this.damagedGroupsSize.size() * 3) {
                        for (String s3 : possibleArrangementsStart) {
                            String str3 = s1.concat(s2.concat(s3.concat(possibleArrangementsStart.get(0))));
                            if (splitToParts(str3).size() == this.damagedGroupsSize.size() * 4) {
                                for (String s4 : possibleArrangementsStart) {
                                    String str4 = s1.concat(s2.concat(s3.concat(s4.concat(possibleArrangementsStart.get(0)))));
                                    if (splitToParts(str4).size() == this.damagedGroupsSize.size() * 5) {
                                        possibleArrangementsFolded.addAll(possibleArrangementsStart.stream().map(s5 -> s1.concat(s2.concat(s3.concat(s4.concat(s5))))).toList());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println(value + " " + damagedGroupsSize + ": " + possibleArrangementsFolded.size());

        return possibleArrangementsFolded;
    }

    private static List<String> splitToParts(String str) {
        return Arrays.stream(str.split("\\.+")).filter(p -> !p.isBlank()).toList();
    }

    public Set<String> getPossibleArrangementsFolded(OnsenRow unknownStart, OnsenRow unknownEnd) {
        possibleArrangementsFolded.clear();

        List<String> possibleArrangements = this.getPossibleArrangements();
        List<String> possibleArrangementsStart = unknownStart.getPossibleArrangements();
        List<String> possibleArrangementsEnd = unknownEnd.getPossibleArrangements();

//        String foldedValue = this.value
//                .concat(unknownStart.value)
//                .concat(unknownStart.value)
//                .concat(unknownStart.value)
//                .concat(unknownStart.value);
//        int totalDamaged = this.damagedGroupsSize.stream().mapToInt(d -> d).sum() * 5;
        int groups = this.damagedGroupsSize.size() * 5;
//        int knownDamaged = 0;
//        int unknown = 0;
//        int bound = foldedValue.length();
//        for (int i = 0; i < bound; i++) {
//            if (Character.toString(foldedValue.charAt(i)).equals(UNKNOWN)) {
//                unknown++;
//            }
//            else if (Character.toString(foldedValue.charAt(i)).equals(DAMAGED)) {
//                knownDamaged++;
//            }
//        }
//        if (unknown == (totalDamaged - knownDamaged)) {
//            String str = value.concat(unknownStart.value).concat(unknownStart.value).concat(unknownStart.value).concat(unknownStart.value);
//            possibleArrangementsFolded.add(str.replaceAll("\\".concat(UNKNOWN), DAMAGED));
//        }
//        else {
            for (String s1 : possibleArrangementsEnd) {
                for (String s2 : possibleArrangementsEnd) {
                    for (String s3 : possibleArrangementsEnd) {
                        for (String s4 : possibleArrangementsEnd) {
                            if (!possibleArrangements.isEmpty()) {
                                String str = s1.concat(s2.concat(s3).concat(s4).concat(possibleArrangements.get(0)));
                                List<String> parts = Arrays.stream(str.split("\\.+")).filter(p -> !p.isBlank()).toList();
                                if (parts.size() == groups) {
                                    possibleArrangementsFolded.addAll(possibleArrangements.stream().map(s5 -> s1.concat(s2.concat(s3.concat(s4.concat(s5))))).toList());
                                }
                            }
                        }
                    }
                }
            }

            for (String s1 : possibleArrangements) {
                for (String s2 : possibleArrangementsStart) {
                    for (String s3 : possibleArrangementsStart) {
                        for (String s4 : possibleArrangementsStart) {
                            if (!possibleArrangementsStart.isEmpty()) {
                                String str = s1.concat(s2.concat(s3).concat(s4).concat(possibleArrangementsStart.get(0)));
                                List<String> parts = Arrays.stream(str.split("\\.+")).filter(p -> !p.isBlank()).toList();
                                if (parts.size() == groups) {
                                    possibleArrangementsFolded.addAll(possibleArrangementsStart.stream().map(s5 -> s1.concat(s2.concat(s3.concat(s4.concat(s5))))).toList());
                                }
                            }
//                            for (String s5 : possibleArrangementsStart) {
//                                String str = s1.concat(s2.concat(s3).concat(s4).concat(s5));
//                                List<String> parts = Arrays.stream(str.split("\\.+")).filter(p -> !p.isBlank()).toList();
//                                if (!possibleArrangementsFolded.contains(str) && parts.size() == groups) {
//                                    possibleArrangementsFolded.add(str);
//                                }
//                            }
                        }
                    }
                }
            }
//        }

        System.out.println(value + " " + damagedGroupsSize + ": " + possibleArrangementsFolded.size());
        return possibleArrangementsFolded;
    }

    public String getValueWithUnknownStart() {
        return UNKNOWN.concat(value);
    }

    public String getValueWithUnknownEnd() {
        return value.concat(UNKNOWN);
    }

    public List<Integer> getDamagedGroupsSize() {
        return damagedGroupsSize;
    }

}
