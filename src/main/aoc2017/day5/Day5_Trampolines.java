package aoc2017.day5;

import lib.Challenge;

public class Day5_Trampolines extends Challenge
{
    public static void main(String[] args)
    {
        Day5_Trampolines day5 = new Day5_Trampolines();
        day5.doOneStarSolution();
        day5.doTwoStarSolution();
    }

    public Day5_Trampolines()
    {
        super("2017/day5-input.txt");
        this.parseFile();
    }

    private Trampoline trampoline;

    @Override
    protected void parseFile()
    {
        trampoline = new Trampoline(getFileContents());
    }

    @Override
    public void doOneStarSolution()
    {
        System.out.println("Number of steps to reach exit: " + trampoline.escapeMaze());
    }

    @Override
    public void doTwoStarSolution()
    {
        System.out.println("Number of steps to reach exit with new rule: " + trampoline.escapeMaze_v2());
    }
}
