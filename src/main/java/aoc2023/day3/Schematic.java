package aoc2023.day3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Schematic {
    private final static String NUMBERS = "0123456789";
    private final static String PERIOD = ".";
    private enum SchematicParseState {
        Period,
        Number,
        Symbol
    }
    private final HashMap<String, String> symbols = new HashMap<>();
    private final List<SchematicNumber> numbers = new ArrayList<>();

    public void parseInput(String input) {
        String[] inputLines = input.split("\\n");
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < inputLines.length; y++) {
            SchematicParseState state = SchematicParseState.Period;
            String inputLine = inputLines[y];
            for (int x = 0; x < inputLine.length(); x++) {
                String currentSymbol = Character.toString(inputLine.charAt(x));
                switch(state) {
                    case Period: {
                        if (NUMBERS.contains(currentSymbol)) {
                            sb.append(currentSymbol);
                            state = SchematicParseState.Number;
                        }
                        else if (!currentSymbol.equals(PERIOD) && !NUMBERS.contains(currentSymbol)) {
                            symbols.put(x + "," + y, currentSymbol);
                            state = SchematicParseState.Symbol;
                        }
                        break;
                    }
                    case Number: {
                        if (NUMBERS.contains(currentSymbol)) {
                            sb.append(currentSymbol);
                        }
                        else {
                            if (currentSymbol.equals(PERIOD)) {
                                state = SchematicParseState.Period;
                            }
                            else {
                                symbols.put(x + "," + y, currentSymbol);
                                state = SchematicParseState.Symbol;
                            }
                            int numberLength = sb.length();
                            numbers.add(new SchematicNumber(Integer.parseInt(sb.toString()), x - numberLength, y));
                            sb = new StringBuilder();
                        }
                        break;
                    }
                    case Symbol: {
                        if (NUMBERS.contains(currentSymbol)) {
                            sb.append(currentSymbol);
                            state = SchematicParseState.Number;
                        }
                        else if (currentSymbol.equals(PERIOD)) {
                            state = SchematicParseState.Period;
                        }
                        else {
                            symbols.put(x + "," + y, currentSymbol);
                        }
                        break;
                    }
                }
                if (x == inputLine.length() - 1 && !sb.isEmpty()) {
                    int numberLength = sb.length();
                    numbers.add(new SchematicNumber(Integer.parseInt(sb.toString()), x - numberLength + 1, y));
                    sb = new StringBuilder();
                }
            }
        }
    }

    public List<Integer> getPartNumbers() {
        List<Integer> partNumbers = new ArrayList<>();
        for (SchematicNumber schematicNumber : numbers) {
            List<String> surroundingCoordinates = schematicNumber.getSurroundingCoordinates();
            for (String surroundingCoordinate : surroundingCoordinates) {
                if (symbols.get(surroundingCoordinate) != null) {
                    partNumbers.add(schematicNumber.getNumber());
                    break;
                }
            }
        }
        return partNumbers;
    }

    public List<Integer> getGearRatios() {
        List<Integer> gearRatios = new ArrayList<>();
        HashMap<String, List<Integer>> gearMap = new HashMap<>();
        for (SchematicNumber schematicNumber : numbers) {
            List<String> surroundingCoordinates = schematicNumber.getSurroundingCoordinates();
            for (String surroundingCoordinate : surroundingCoordinates) {
                if (symbols.get(surroundingCoordinate) != null) {
                    gearMap.putIfAbsent(surroundingCoordinate, new ArrayList<>());
                    gearMap.get(surroundingCoordinate).add(schematicNumber.getNumber());
                    break;
                }
            }
        }
        for (List<Integer> gearMapNumbers : gearMap.values()) {
            if (gearMapNumbers.size() == 2) {
                gearRatios.add(gearMapNumbers.get(0) * gearMapNumbers.get(1));
            }
        }
        return gearRatios;
    }
}
