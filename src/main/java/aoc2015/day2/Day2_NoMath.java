package aoc2015.day2;

import lib.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Day2_NoMath extends Challenge {
    public static void main(String[] args) {
        Day2_NoMath day2 = new Day2_NoMath();
        day2.doOneStarSolution();
        day2.doTwoStarSolution();
    }

    public Day2_NoMath() {
        super("2015/day2-input.txt");
        this.parseFile();
    }

    private final List<PresentSize> presentSizeList = new ArrayList<>();

    @Override
    protected void parseFile() {
        String[] lines = getFileContents().split("\\n");
        for (String line : lines) {
            presentSizeList.add(new PresentSize(line));
        }
    }

    @Override
    public void doOneStarSolution() {
        int totalWrappingPaperNeeded = presentSizeList.stream().mapToInt(presentSize -> presentSize.computeSurfaceArea() + presentSize.getAreaOfSmallestSide()).sum();
        System.out.println("Total wrapping paper size: " + totalWrappingPaperNeeded);
    }

    @Override
    public void doTwoStarSolution() {
        int totalRibbonNeeded = presentSizeList.stream().mapToInt(presentSize -> presentSize.getPerimeterOfSmallestSide() + presentSize.getVolume()).sum();
        System.out.println("Total ribbon length: " + totalRibbonNeeded);
    }
}
