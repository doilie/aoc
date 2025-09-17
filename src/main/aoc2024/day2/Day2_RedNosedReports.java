package aoc2024.day2;

import lib.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Day2_RedNosedReports extends Challenge {
    public static void main(String[] args)
    {
        Day2_RedNosedReports day1 = new Day2_RedNosedReports();
        day1.doOneStarSolution();
        day1.doTwoStarSolution();
    }

    public Day2_RedNosedReports() {
        super("2024/day2-input.txt");
        this.parseFile();
    }

    private final List<LevelsReport> reports = new ArrayList<>();

    @Override
    public void parseFile()
    {

        String[] lines = getFileContents().split("\\n");
        for (String line : lines)
        {
            reports.add(LevelsReport.getLevelsReportFromString(line));
        }
    }

    @Override
    public void doOneStarSolution()
    {
        System.out.println("Number of safe reports : " + reports.stream().filter(LevelsReport::isSafe).count());
    }

    @Override
    public void doTwoStarSolution()
    {
        System.out.println("Number of safe reports (dampened) : " + reports.stream().filter(LevelsReport::isSafeWithoutOneLevel).count());
    }

}
