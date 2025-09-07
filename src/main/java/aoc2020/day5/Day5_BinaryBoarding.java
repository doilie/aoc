package aoc2020.day5;

import lib.Challenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day5_BinaryBoarding extends Challenge {
    public static void main(String[] args)
    {
        Day5_BinaryBoarding day5 = new Day5_BinaryBoarding();
        day5.doOneStarSolution();
        day5.doTwoStarSolution();
    }

    public Day5_BinaryBoarding()
    {
        super("2020/day5-input.txt");
        this.parseFile();
    }

    private final List<BoardingPass> boardingPasses = new ArrayList<>();

    @Override
    protected void parseFile()
    {
        String[] lines = getFileContents().split("\\n");
        for (String line : lines)
        {
            boardingPasses.add(new BoardingPass(line));
        }
    }

    @Override
    public void doOneStarSolution()
    {
        System.out.println("Highest seat ID on a boarding pass: " + boardingPasses.stream().map(BoardingPass::getSeatId).max(Integer::compareTo).get());
    }

    @Override
    public void doTwoStarSolution()
    {
        List<Integer> seatIds = new ArrayList<>(boardingPasses.stream().map(BoardingPass::getSeatId).toList());
        Collections.sort(seatIds);
        int missingSeat = 0;
        for (int i = 0; i < seatIds.size() - 1; i++)
        {
            if (seatIds.get(i) + 1 != seatIds.get(i + 1))
            {
                missingSeat = seatIds.get(i) + 1;
                break;
            }
        }
        System.out.println("My seat ID: " + missingSeat);
    }
}
