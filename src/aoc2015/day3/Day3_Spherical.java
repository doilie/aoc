package aoc2015.day3;

import lib.Challenge;

import java.util.HashSet;
import java.util.Set;

public class Day3_Spherical extends Challenge {
    public static void main(String[] args) {
        Day3_Spherical day3 = new Day3_Spherical();
        day3.doOneStarSolution();
        day3.doTwoStarSolution();
    }

    public Day3_Spherical() {
        super("2015/day3-input.txt");
        this.parseFile();
    }

    @Override
    protected void parseFile() {
    }

    @Override
    public void doOneStarSolution() {
        Sphere sphere = new Sphere();
        String contents = getFileContents();
        for (int i = 0; i < contents.length(); i++) {
            sphere.move(Character.toString(contents.charAt(i)));
        }
        int visitedPositions = sphere.getVisitedPositions().size();
        System.out.println("Visited Positions: " + visitedPositions);
    }

    @Override
    public void doTwoStarSolution() {
        Sphere sphereHuman = new Sphere();
        Sphere sphereRobot = new Sphere();

        String contents = getFileContents();
        for (int i = 0; i < contents.length(); i++) {
            if (i % 2 == 0)
            {
                sphereHuman.move(Character.toString(contents.charAt(i)));
            }
            else
            {
                sphereRobot.move(Character.toString(contents.charAt(i)));
            }
        }
        Set<String> allVisitedPositions = new HashSet<>();
        allVisitedPositions.addAll(sphereHuman.getVisitedPositions());
        allVisitedPositions.addAll(sphereRobot.getVisitedPositions());
        int visitedPositions = allVisitedPositions.size();
        System.out.println("Visited Positions with Robo-Santa: " + visitedPositions);
    }
}
