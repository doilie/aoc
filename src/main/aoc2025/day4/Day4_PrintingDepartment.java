package aoc2025.day4;

import lib.Challenge;

public class Day4_PrintingDepartment extends Challenge {
    public static void main(String[] args)
    {
        Day4_PrintingDepartment day4 = new Day4_PrintingDepartment();
        day4.doOneStarSolution();
        day4.doTwoStarSolution();
    }

    public Day4_PrintingDepartment() {
        super("2025/day4-input.txt");
        this.parseFile();
    }

        @Override
    public void parseFile()
    {

    }

    @Override
    public void doOneStarSolution() {
        ToiletPaperGrid toiletPaperGrid = new ToiletPaperGrid(this.getFileContents());
        System.out.println("Rolls of paper that can be accessed by forklift: " + toiletPaperGrid.getToiletPaperWithLessThanNumAdjacent(4).size());
    }

    @Override
    public void doTwoStarSolution() {

    }

}
