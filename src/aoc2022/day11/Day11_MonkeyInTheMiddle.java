package aoc2022.day11;

import aoc2022.day11.monkey.MonkeyGang;
import aoc2022.day11.monkey.MonkeyParser;
import lib.Challenge;

import java.util.Arrays;
import java.util.List;

public class Day11_MonkeyInTheMiddle extends Challenge {
    public static void main(String[] args) {
        Day11_MonkeyInTheMiddle day11 = new Day11_MonkeyInTheMiddle();
        day11.doOneStarSolution();
        day11.doTwoStarSolution();
    }

    private MonkeyGang monkeyGang;

    public Day11_MonkeyInTheMiddle() {
        super("2022/day11-input.txt");
    }

    @Override
    protected void parseFile() {
        monkeyGang = new MonkeyGang();
        List<String> lines = Arrays.asList(this.getFileContents().split("\n"));
        for (int i = 0; i < lines.size(); i += MonkeyParser.MonkeyRuleLines) {
            monkeyGang.addMonkey(lines.subList(i, Math.min(i + MonkeyParser.MonkeyRuleLines, lines.size())));
            i++; // blank line
        }
    }

    @Override
    public void doOneStarSolution() {
        parseFile();
        doSolution(20);
    }

    @Override
    public void doTwoStarSolution() {
        parseFile();
        monkeyGang.removeRelief();
        doSolution(10000);
    }

    private void doSolution(int rounds) {
        monkeyGang.setGangMembersMaxWorryValue();
        monkeyGang.inspectEachMonkey(rounds);
        System.out.println("Product of 2 max number of inspection count (" + rounds + " rounds): " + monkeyGang.getProductOfMax2InspectionCounts());
    }
}
