package aoc2023.day9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberSequence {
    private final List<Integer> numbers = new ArrayList<>();

    public NumberSequence(String line) {
        String[] lineParts = line.split("\\s+");
        numbers.addAll(Arrays.stream(lineParts).map(Integer::parseInt).toList());
    }

    public int getNextNumber() {
        List<Integer> tempList = new ArrayList<>(numbers);
        int nextNumber = 0;

        for (int i = numbers.size(); i >= 1; i--) {
            if (tempList.stream().filter(num -> num == 0).count() == tempList.size()) {
                break;
            }
            else {
                nextNumber += tempList.get(tempList.size() - 1);
                tempList = getDifferences(tempList);
            }
        }

        return nextNumber;
    }

    public int getFirstNumber() {
        List<List<Integer>> sequencesToZero = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>(numbers);

        for (int i = numbers.size(); i >= 1; i--) {
            sequencesToZero.add(tempList);
            if (tempList.stream().filter(num -> num == 0).count() == tempList.size()) {
                break;
            }
            else {
                tempList = getDifferences(tempList);
            }
        }

        int firstNumber = 0;
        for (int i = sequencesToZero.size() - 1; i >= 0; i--) {
            firstNumber = sequencesToZero.get(i).get(0) - firstNumber;
        }

        return firstNumber;
    }


    private static List<Integer> getDifferences(List<Integer> numberList) {
        List<Integer> differences = new ArrayList<>();

        for (int i = 0; i < numberList.size() - 1; i++) {
            differences.add(numberList.get(i + 1) - numberList.get(i));
        }

        return differences;
    }
}
