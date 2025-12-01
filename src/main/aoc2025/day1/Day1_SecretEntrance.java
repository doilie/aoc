package aoc2025.day1;

import lib.Challenge;

public class Day1_SecretEntrance extends Challenge {
    public static void main(String[] args)
    {
        Day1_SecretEntrance day1 = new Day1_SecretEntrance();
        day1.doOneStarSolution();
        day1.doTwoStarSolution();
    }

    public Day1_SecretEntrance() {
        super("2025/day1-input.txt");
        this.parseFile();
    }

    @Override
    public void parseFile()
    {
    }

    @Override
    public void doOneStarSolution() {
        SafeDial safeDial = new SafeDial(50);
        String[] lines = this.getFileContents().split("\\n");
        int zeroPosCtr = 0;
        for (String line : lines)
        {
            if (!line.isEmpty())
            {
                safeDial.turn(line);
                if (safeDial.getCurrentPosition() == 0)
                {
                    zeroPosCtr++;
                }
            }
        }
        System.out.println("Password : " + zeroPosCtr);
    }

    @Override
    public void doTwoStarSolution() {

    }

}
