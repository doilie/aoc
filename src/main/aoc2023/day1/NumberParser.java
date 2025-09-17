package aoc2023.day1;

import java.util.ArrayList;
import java.util.List;

public class NumberParser {
    private final static List<String> numbersAsSymbol = List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
    private final static List<String> numbersAsString = List.of("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
    private final String line;

    public NumberParser(String line) {
        this.line = line;
    }

    public int getNumberFromSymbols() {
        return getNumberFromValidNumbers(numbersAsSymbol);
    }

    public int getNumberFromSymbolsAndWords() {
        List<String> validNumberList = new ArrayList<>(numbersAsString);
        validNumberList.addAll(numbersAsSymbol);
        return getNumberFromValidNumbers(validNumberList);
    }

    private int getNumberFromValidNumbers(List<String> validNumberList) {
        int lowestIndex = Integer.MAX_VALUE;
        String firstNumber = "";
        int highestIndex = -1;
        String lastNumber = "";

        for (String validNumber : validNumberList) {
            int validNumberIndex = line.indexOf(validNumber);
            if (validNumberIndex != -1) {
                if (validNumberIndex < lowestIndex) {
                    lowestIndex = validNumberIndex;
                    firstNumber = validNumber;
                }
                validNumberIndex = line.lastIndexOf(validNumber);
                if (validNumberIndex > highestIndex) {
                    highestIndex = validNumberIndex;
                    lastNumber = validNumber;
                }
            }
        }

        firstNumber = getNumberSymbol(firstNumber);
        lastNumber = getNumberSymbol(lastNumber);

        return Integer.parseInt(firstNumber + lastNumber);
    }

    private String getNumberSymbol(String foundNumber) {
        return !numbersAsSymbol.contains(foundNumber) ? numbersAsSymbol.get(numbersAsString.indexOf(foundNumber)): foundNumber;
    }
}
