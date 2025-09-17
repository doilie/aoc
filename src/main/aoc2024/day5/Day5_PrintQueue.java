package aoc2024.day5;

import lib.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Day5_PrintQueue extends Challenge {
    public static void main(String[] args)
    {
        Day5_PrintQueue day5 = new Day5_PrintQueue();
        day5.doOneStarSolution();
        day5.doTwoStarSolution();
    }

    public Day5_PrintQueue() {
        super("2024/day5-input.txt");
        this.parseFile();
    }

    private final List<PagesToPrint> pagesToPrintList = new ArrayList<>();

    @Override
    protected void parseFile()
    {
        String[] parts = getFileContents().split("\n\n");
        if (parts.length == 2)
        {
            PageOrderingRules pageOrderingRules = new PageOrderingRules(parts[0]);
            String[] lines = parts[1].split("\\n");
            for (String line : lines)
            {
                pagesToPrintList.add(new PagesToPrint(line, pageOrderingRules));
            }
        }
    }

    @Override
    public void doOneStarSolution()
    {
        System.out.println("Sum of middle pages of correctly-ordered updates : " + pagesToPrintList.stream()
                .filter(PagesToPrint::isInCorrectOrder)
                .map(PagesToPrint::getMiddlePage)
                .mapToInt(Integer::intValue)
                .sum());
    }

    @Override
    public void doTwoStarSolution()
    {
        int sum = 0;
        for (PagesToPrint p : pagesToPrintList)
        {
            if (!p.isInCorrectOrder())
            {
                p.sort();
                sum += p.getMiddlePage();
            }
        }
        System.out.println("Sum of middle pages of newly sorted updates : " + sum);
    }
}
