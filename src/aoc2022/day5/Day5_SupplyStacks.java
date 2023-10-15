package aoc2022.day5;

import aoc2022.day5.supplystacks.SupplyStacks;
import lib.Challenge;

import java.util.Arrays;
import java.util.List;

public class Day5_SupplyStacks extends Challenge {
    public static void main(String[] args)  {
        Day5_SupplyStacks day5 = new Day5_SupplyStacks();
        day5.doOneStarSolution();
        day5.doTwoStarSolution();
    }

    public Day5_SupplyStacks() {
        super("2022/day5-input.txt");
    }

    private final SupplyStacks supplyStacks = new SupplyStacks();


    @Override
    protected void parseFile() {
        final int stackStartRow = 9;
        final int movesStartRow = 11;

        String[] lines = this.getFileContents().split("\n");
        buildStacks(Arrays.asList(lines).subList(0, stackStartRow));
        buildMoves(Arrays.asList(lines).subList(movesStartRow - 1, lines.length));
    }

    private void buildStacks(List<String> lines) {
        for (int i = lines.size() - 1; i >= 0; i--) {
            String line = lines.get(i);
            if (i == lines.size() - 1) {
                int numStacks = line.split(" {3}").length;
                for (int numStack = 0; numStack < numStacks; numStack++) {
                    supplyStacks.createNewStack();
                }
            }
            else {
                for (int j = 1; j < line.length(); j += 4) {
                    if (line.charAt(j) != ' ') {
                        supplyStacks.addToStack((j - 1) / 4, Character.toString(line.charAt(j)));
                    }
                }
            }
        }
    }

    private void buildMoves(List<String> lines) {
        for (String line : lines) {
            String[] move = line.split(" ");
            if (move.length == 6) {
                supplyStacks.addMove(Integer.parseInt(move[1]), Integer.parseInt(move[3]) - 1, Integer.parseInt(move[5]) - 1);
            }
        }
    }

    @Override
    public void doOneStarSolution() {
        parseFile();
        supplyStacks.moveCratesOneByOne();
        System.out.println("Crates on top - moved one by one: " + supplyStacks.getTopCrates());
    }

    @Override
    public void doTwoStarSolution() {
        supplyStacks.reset();
        parseFile();
        supplyStacks.moveCratesByBatch();
        System.out.println("Crates on top - moved by batch: " + supplyStacks.getTopCrates());
    }
}
