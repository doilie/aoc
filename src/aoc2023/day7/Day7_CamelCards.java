package aoc2023.day7;

import lib.Challenge;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class Day7_CamelCards extends Challenge {
    public static void main(String[] args)  {
        Day7_CamelCards day7 = new Day7_CamelCards();
        day7.doOneStarSolution();
        day7.doTwoStarSolution();
    }

    public Day7_CamelCards() {
        super("2023/day7-input.txt");
        this.parseFile();
    }

    private TreeSet<CamelCardHand> camelCardHands;

    @Override
    public void parseFile() {
        String[] lines = this.getFileContents().split("\\n");
        camelCardHands = new TreeSet<>();
        for (String line : lines) {
            camelCardHands.add(new CamelCardHand(line));
        }
    }

    @Override
    public void doOneStarSolution() {
        CamelCardHand.useNormalMode();
        parseFile();
        List<CamelCardHand> camelCardHandsRanked = camelCardHands.stream().toList();
        int totalWinnings = IntStream.rangeClosed(1, camelCardHandsRanked.size()).map(i -> camelCardHandsRanked.get(i - 1).getBid() * i).sum();
        System.out.println("Total Winnings: " + totalWinnings);
    }

    @Override
    public void doTwoStarSolution() {
        CamelCardHand.useJokerMode();
        parseFile();
        List<CamelCardHand> camelCardHandsRanked = camelCardHands.stream().toList();
        int totalWinnings = IntStream.rangeClosed(1, camelCardHandsRanked.size()).map(i -> camelCardHandsRanked.get(i - 1).getBid() * i).sum();
        System.out.println("Total Winnings With Joker: " + totalWinnings);
    }

}
