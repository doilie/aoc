package aoc2020.day6;

import lib.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Day6_CustomCustoms extends Challenge {
    public static void main(String[] args)
    {
        Day6_CustomCustoms day6 = new Day6_CustomCustoms();
        day6.doOneStarSolution();
        day6.doTwoStarSolution();
    }

    public Day6_CustomCustoms()
    {
        super("2020/day6-input.txt");
        this.parseFile();
    }

    private final List<GroupCustomsDeclaration> groupCustomsDeclarations = new ArrayList<>();

    @Override
    protected void parseFile()
    {
        String[] groups = getFileContents().split("\\r\\n\\r\\n");
        for (String group : groups)
        {
            groupCustomsDeclarations.add(new GroupCustomsDeclaration(group));
        }
    }

    @Override
    public void doOneStarSolution()
    {
        System.out.println("Sum of yes answers: " + groupCustomsDeclarations.stream().map(GroupCustomsDeclaration::getYesAnswers).mapToInt(Integer::intValue).sum());
    }

    @Override
    public void doTwoStarSolution()
    {
        System.out.println("Sum of common yes answers: " + groupCustomsDeclarations.stream().map(GroupCustomsDeclaration::getCommonYesAnswers).mapToInt(Integer::intValue).sum());
    }
}
