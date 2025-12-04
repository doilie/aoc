package aoc2025.day3;

import lib.Challenge;

import java.util.HashSet;
import java.util.Set;

public class Day3_Lobby extends Challenge {
    public static void main(String[] args)
    {
        Day3_Lobby day3 = new Day3_Lobby();
        day3.doOneStarSolution();
        day3.doTwoStarSolution();
    }

    public Day3_Lobby() {
        super("2025/day3-input.txt");
        this.parseFile();
    }

    private final Set<BatteryBank> batteryBanks = new HashSet<>();

        @Override
    public void parseFile()
    {
        String[] lines = this.getFileContents().split("\\n");
        for (String line : lines)
        {
            if (!line.isEmpty())
            {
                batteryBanks.add(new BatteryBank(line));
            }
        }
    }

    @Override
    public void doOneStarSolution() {
        batteryBanks.forEach(batteryBank -> batteryBank.setNumberOfBatteriesOn(2));
        System.out.println("Total output joltage for 2 batteries: " + batteryBanks.stream().map(BatteryBank::getLargestPossibleJoltage).mapToLong(Long::longValue).sum());
    }

    @Override
    public void doTwoStarSolution() {
        batteryBanks.forEach(batteryBank -> batteryBank.setNumberOfBatteriesOn(12));
        System.out.println("Total output joltage for 12 batteries: " + batteryBanks.stream().map(BatteryBank::getLargestPossibleJoltage).mapToLong(Long::longValue).sum());
    }

}
