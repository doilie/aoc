package aoc2025.day8;

import lib.Challenge;

import java.util.List;
import java.util.Set;

public class Day8_Playground extends Challenge {
    public static void main(String[] args)
    {
        Day8_Playground day6 = new Day8_Playground();
        day6.doOneStarSolution();
        day6.doTwoStarSolution();
    }

    public Day8_Playground() {
        super("2025/day8-input.txt");
        this.parseFile();
    }

        @Override
    public void parseFile()
    {
    }

    @Override
    public void doOneStarSolution() {
        Playground playground = new Playground(this.getFileContents());
        List<Set<String>> circuits = playground.getCircuits(1000);
        int largestCircuitsProduct = 1;
        for (int i = 0; i < 3; i++)
        {
            largestCircuitsProduct *= circuits.get(i).size();
        }
        System.out.println("Largest circuit product: " + largestCircuitsProduct);
    }

    @Override
    public void doTwoStarSolution() {
        Playground playground = new Playground(this.getFileContents());
        String completingConnection = playground.getCompletingConnection();
        String[] points = completingConnection.split("-");
        String[] firstPoint = points[0].split(",");
        String[] secondPoint = points[1].split(",");
        long completingConnectionXProduct = Long.parseLong(firstPoint[0]) * Long.parseLong(secondPoint[0]);
        System.out.println("Completing connection x product: " + completingConnectionXProduct);
    }

}
