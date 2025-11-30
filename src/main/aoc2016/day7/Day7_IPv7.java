package aoc2016.day7;

import lib.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Day7_IPv7 extends Challenge {
    public static void main(String[] args)
    {
        Day7_IPv7 day7 = new Day7_IPv7();
        day7.doOneStarSolution();
        day7.doTwoStarSolution();
    }

    public Day7_IPv7()
    {
        super("2016/day7-input.txt");
        this.parseFile();
    }

    private final List<IPv7Address> iPv7AddressList = new ArrayList<>();

    @Override
    protected void parseFile()
    {
        String[] lines = this.getFileContents().split("\\n");
        for (String line : lines)
        {
            if (!line.isEmpty())
            {
                iPv7AddressList.add(new IPv7Address(line));
            }
        }
    }

    @Override
    public void doOneStarSolution()
    {
        System.out.println("Number of IPs supporting TLS: " + iPv7AddressList.stream().filter(IPv7Address::isTLS).toList().size());
    }

    @Override
    public void doTwoStarSolution() {
        System.out.println("Number of IPs supporting SSL: " + iPv7AddressList.stream().filter(IPv7Address::isSSL).toList().size());
    }

}
