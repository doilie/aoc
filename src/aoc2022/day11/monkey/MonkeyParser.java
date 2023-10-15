package aoc2022.day11.monkey;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MonkeyParser {
    public final static int MonkeyRuleLines = 6;
    private enum MonkeyRuleRow {
        Monkey(0),
        StartingItems(1),
        Operation(2),
        DivisibleBy(3),
        MonkeyTrue(4),
        MonkeyFalse(5);


        final int row;

        MonkeyRuleRow(int row) {
            this.row = row;
        }
    }

    private enum MonkeyRowRulePartIdx {
        Type(1),
        StartingRowItemStart(3),
        OperationOperator(5),
        OperationValue(6);

        final int idx;

        MonkeyRowRulePartIdx(int idx) {
            this.idx = idx;
        }
    }

    private final List<String> lines;
    private Monkey newMonkey;

    public MonkeyParser(List<String> lines) {
        this.lines = lines;
    }

    public Monkey parse() {
        if (lines.size() == MonkeyRuleLines) {
            newMonkey = createMonkey();
            if (newMonkey != null) {
                parseStartingItems();
                parseOperation();
                parseTestConditions();
            }
        }
        return newMonkey;
    }

    private Monkey createMonkey() {
        String monkeyRow = lines.get(MonkeyRuleRow.Monkey.row);
        List<String> lineParts = splitByWhiteSpace(monkeyRow);
        if (lineParts.get(0).startsWith("Monkey")) {
            return new Monkey();
        }
        return null;
    }

    private void parseStartingItems() {
        if (newMonkey != null) {
            String startingItemsRow = lines.get(MonkeyRuleRow.StartingItems.row);
            List<String> lineParts = splitByWhiteSpace(startingItemsRow);
            List<Long> items = lineParts.subList(MonkeyRowRulePartIdx.StartingRowItemStart.idx, lineParts.size()).stream()
                    .map(itemString ->
                            Long.valueOf(itemString.replace(',', ' ').trim())
                    ).collect(Collectors.toList());
            newMonkey.addItems(items);
        }
    }

    private void parseOperation() {
        if (newMonkey != null) {
            String operationRow = lines.get(MonkeyRuleRow.Operation.row);
            List<String> lineParts = splitByWhiteSpace(operationRow);
            Pattern pattern = Pattern.compile("\\d+");
            String operator = lineParts.get(MonkeyRowRulePartIdx.OperationOperator.idx);
            String value = lineParts.get(MonkeyRowRulePartIdx.OperationValue.idx);
            if (pattern.matcher(value).matches()) {
                final long i = Long.parseLong(value);
                switch (operator) {
                    case "+":
                        newMonkey.setOperationFunction(val -> val + i);
                        break;
                    case "-":
                        newMonkey.setOperationFunction(val -> val - i);
                        break;
                    case "*":
                        newMonkey.setOperationFunction(val -> val * i);
                        break;
                }
            }
            else {
                switch (operator) {
                    case "+":
                        newMonkey.setOperationFunction(val -> val + val);
                        break;
                    case "-":
                        newMonkey.setOperationFunction(val -> 0L);
                        break;
                    case "*":
                        newMonkey.setOperationFunction(val -> val * val);
                        break;
                }
            }
        }
    }

    private void parseTestConditions() {
        if (newMonkey != null) {
            String divisibleByRow = lines.get(MonkeyRuleRow.DivisibleBy.row);
            List<String> lineParts = splitByWhiteSpace(divisibleByRow);
            Long divisibleByValue = Long.valueOf(lineParts.get(lineParts.size() - 1));
            newMonkey.setDivisibleByValue(divisibleByValue);

            String monkeyTrueRow = lines.get(MonkeyRuleRow.MonkeyTrue.row);
            lineParts = splitByWhiteSpace(monkeyTrueRow);
            newMonkey.setMonkeyTrue(Integer.parseInt(lineParts.get(lineParts.size() - 1)));

            String monkeyFalseRow = lines.get(MonkeyRuleRow.MonkeyFalse.row);
            lineParts = splitByWhiteSpace(monkeyFalseRow);
            newMonkey.setMonkeyFalse(Integer.parseInt(lineParts.get(lineParts.size() - 1)));
        }
    }

    private List<String> splitByWhiteSpace(String line) {
        return Arrays.asList(line.split("\\s+"));
    }
}
