package aoc2024.day8;

import lib.Challenge;

public class Day8_ResonantCollinearity extends Challenge
{
    public static void main(String[] args)
    {
        Day8_ResonantCollinearity day8 = new Day8_ResonantCollinearity();
        day8.doOneStarSolution();
        day8.doTwoStarSolution();
    }

    public Day8_ResonantCollinearity() {
        super("2024/day8-input.txt");
        this.parseFile();
    }



    @Override
    protected void parseFile()
    {
        String[] lines = getFileContents().split("\\n");
        AntennaGroup.setSize(lines.length);
    }

    @Override
    public void doOneStarSolution()
    {
        AntennaField antennaField = new AntennaField(getFileContents());
        System.out.println("Number of antinodes : " + antennaField.getAntinodePositions().size());
    }

    @Override
    public void doTwoStarSolution()
    {
//        System.out.println("Total calibration result with concatenate: " + getCalibrationResult(List.of(Expression.MULTIPLY, Expression.ADD, Expression.CONCATENATE)));
    }
}
