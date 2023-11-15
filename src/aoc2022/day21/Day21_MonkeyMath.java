package aoc2022.day21;

import lib.Challenge;

import java.util.Hashtable;

public class Day21_MonkeyMath extends Challenge {
    public static void main(String[] args) {
        Day21_MonkeyMath day21 = new Day21_MonkeyMath();
        day21.doOneStarSolution();
        day21.doTwoStarSolution();
    }

    public Day21_MonkeyMath() {
        super("2022/day21-input.txt");
        parseFile();
    }

    private final Hashtable<String, Monkey> monkeys = new Hashtable<>();

    @Override
    protected void parseFile() {
        String[] lines = getFileContents().split("\\n");
        for (String line : lines) {
            Monkey monkey = Monkey.buildMonkey(line, monkeys);
            if (monkey != null) {
                monkeys.put(monkey.getName(), monkey);
            }
        }
    }

    @Override
    public void doOneStarSolution() {
        System.out.println("Root monkey yells: " + monkeys.get("root").doTask());
    }

    @Override
    public void doTwoStarSolution() {
        System.out.println("Human yells: " + Monkey.getReplacementValueForMonkey("humn", monkeys));
    }
}
