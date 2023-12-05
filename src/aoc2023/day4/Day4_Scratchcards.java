package aoc2023.day4;

import lib.Challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Day4_Scratchcards extends Challenge {
    public static void main(String[] args)  {
        Day4_Scratchcards day4 = new Day4_Scratchcards();
        day4.doOneStarSolution();
        day4.doTwoStarSolution();
    }

    public Day4_Scratchcards() {
        super("2023/day4-input.txt");
        this.parseFile();
    }

    private final List<Scratchcard> scratchcards = new ArrayList<>();

    @Override
    public void parseFile() {
        String[] lines = this.getFileContents().split("\\n");
        for (String line : lines) {
            scratchcards.add(new Scratchcard(line));
        }
    }

    @Override
    public void doOneStarSolution() {
        int points = scratchcards.stream().mapToInt(Scratchcard::getPoints).sum();
        System.out.println("Sum of scratch card points: " + points);
    }

    @Override
    public void doTwoStarSolution() {
        HashMap<Integer, Integer> matchesIndex = scratchcards.stream().collect(Collectors.toMap(Scratchcard::getId, Scratchcard::getMatches, (a, b) -> b, HashMap::new));
        HashMap<Integer, Integer> cardCount = scratchcards.stream().collect(Collectors.toMap(Scratchcard::getId, scratchcard -> 1, (a, b) -> b, HashMap::new));
        for (int i = 1; i <= scratchcards.size(); i++) {
            int matches = matchesIndex.get(i);
            int numCards = cardCount.get(i);
            for (int cardIdx = 0; cardIdx < numCards; cardIdx++) {
                for (int cardToRepro = i + 1; cardToRepro <= i + matches; cardToRepro++) {
                    cardCount.put(cardToRepro, cardCount.get(cardToRepro) + 1);
                }
            }
        }
        int totalCards = cardCount.values().stream().mapToInt(numCards -> numCards).sum();
        System.out.println("Total number of cards: " + totalCards);
    }

}
