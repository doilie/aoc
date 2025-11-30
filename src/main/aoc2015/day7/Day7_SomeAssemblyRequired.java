package aoc2015.day7;

import lib.Challenge;

public class Day7_SomeAssemblyRequired extends Challenge
{
        public static void main(String[] args)
    {
        Day7_SomeAssemblyRequired day7 = new Day7_SomeAssemblyRequired();
        day7.doOneStarSolution();
        day7.doTwoStarSolution();
    }

    public Day7_SomeAssemblyRequired()
    {
        super("2015/day7-input.txt");
        this.parseFile();
    }

    @Override
    protected void parseFile()
    {

    }

    @Override
    public void doOneStarSolution() {
        BitwiseCircuit bitwiseCircuit = new BitwiseCircuit(this.getFileContents().split("\\n"));
        bitwiseCircuit.runCircuit();
        System.out.println("Signal provided to wire " + bitwiseCircuit.getResult("a"));
    }

    @Override
    public void doTwoStarSolution() {

    }

}
