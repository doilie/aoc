package aoc2021.day4;

import lib.Challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Day4_GiantSquid extends Challenge {
    public static void main(String[] args)
    {
        Day4_GiantSquid day4 = new Day4_GiantSquid();
        day4.doOneStarSolution();
        day4.doTwoStarSolution();
    }


    public Day4_GiantSquid()
    {
        super("2021/day4-input.txt");
        this.parseFile();
    }

    private final List<Integer> calledNumbers = new ArrayList<>();
    private final List<BingoCard> bingoCards = new ArrayList<>();

    @Override
    public void parseFile()
    {
        String[] lines = getFileContents().split("\\n\\n");
        if (lines.length > 2)
        {
            calledNumbers.addAll(Arrays.stream(lines[0].split(",")).map(Integer::parseInt).toList());
            for (int i = 1; i < lines.length; i++)
            {
                String line = lines[i];
                bingoCards.add(new BingoCard(line));
            }
        }

    }

    @Override
    public void doOneStarSolution()
    {
        BingoCard winningCard = null;
        int winningNumber = 0;
        for (int calledNumber : calledNumbers)
        {
            bingoCards.forEach(bingoCard -> bingoCard.markNumber(calledNumber));
            Optional<BingoCard> winner = bingoCards.stream().filter(BingoCard::hasWon).findFirst();
            if (winner.isPresent())
            {
                winningCard = winner.get();
                winningNumber = calledNumber;
                break;
            }
        }
        if (winningCard != null)
        {
            System.out.println("Score of winning board: " + winningCard.computeScore(winningNumber));
        }
    }

    @Override
    public void doTwoStarSolution()
    {
        List<BingoCard> winningCards = new ArrayList<>();
        int winningNumber = 0;
        for (int calledNumber : calledNumbers)
        {
            bingoCards.forEach(bingoCard -> bingoCard.markNumber(calledNumber));
            List<BingoCard> currentWinningCards = bingoCards.stream().filter(BingoCard::hasWon).toList();
            winningCards.addAll(currentWinningCards);
            bingoCards.removeAll(currentWinningCards);
            if (bingoCards.isEmpty())
            {
                winningNumber = calledNumber;
                break;
            }
        }
        if (!winningCards.isEmpty())
        {
            System.out.println("Score of last winning board: " + winningCards.get(winningCards.size() - 1).computeScore(winningNumber));
        }
    }
}
