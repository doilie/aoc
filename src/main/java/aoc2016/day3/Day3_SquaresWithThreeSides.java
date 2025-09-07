package aoc2016.day3;

import lib.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Day3_SquaresWithThreeSides extends Challenge {
    public static void main(String[] args) {
        Day3_SquaresWithThreeSides day3 = new Day3_SquaresWithThreeSides();
        day3.doOneStarSolution();
        day3.doTwoStarSolution();
    }

    public Day3_SquaresWithThreeSides() {
        super("2016/day3-input.txt");
        this.parseFile();
    }

    @Override
    protected void parseFile() {

    }

    @Override
    public void doOneStarSolution() {
        List<TriangleChecker> triangleCheckerList = new ArrayList<>();
        String[] sidesList = getFileContents().split("\\n");
        for (String sides : sidesList) {
            triangleCheckerList.add(TriangleChecker.createOneTriangleFromRow((sides)));
        }
        System.out.println("Number of possible triangles from rows: " + triangleCheckerList.stream().filter(TriangleChecker::isValid).count());
    }

    @Override
    public void doTwoStarSolution() {
        List<TriangleChecker> triangleCheckerList = new ArrayList<>();
        String[] sidesList = getFileContents().split("\\n");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sidesList.length; i++) {
            if (i % 3 == 0 && i != 0)
            {
                triangleCheckerList.addAll(TriangleChecker.createThreeTrianglesFromRows(sb.toString()));
                sb.setLength(0);
            }
            sb.append(sidesList[i]);
            sb.append("\n");
        }
        // for last 3 rows
        triangleCheckerList.addAll(TriangleChecker.createThreeTrianglesFromRows(sb.toString()));
        System.out.println("Number of possible triangles from columns: " + triangleCheckerList.stream().filter(TriangleChecker::isValid).count());
    }
}
