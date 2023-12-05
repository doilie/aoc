package aoc2023.day2;

import lib.Challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day2_CubeConundrum extends Challenge {
    public static void main(String[] args)  {
        Day2_CubeConundrum day2 = new Day2_CubeConundrum();
        day2.doOneStarSolution();
        day2.doTwoStarSolution();
    }

    public Day2_CubeConundrum() {
        super("2023/day2-input.txt");
        this.parseFile();
    }

    private final List<CubeGame> cubeGames = new ArrayList<>();

    @Override
    public void parseFile() {
        String[] lines = this.getFileContents().split("\n");
        for (String line : lines) {
            cubeGames.add(new CubeGame(line));
        }
    }

    @Override
    public void doOneStarSolution() {
        HashMap<String, Integer> gameChecker = new HashMap<>();
        gameChecker.put("red", 12);
        gameChecker.put("green", 13);
        gameChecker.put("blue", 14);
        int validGameIdSum = 0;
        for (CubeGame cubeGame : cubeGames) {
            if (cubeGame.checkCubeSets(gameChecker)) {
                validGameIdSum += cubeGame.getId();
            }
        }
        System.out.println("Valid Game ID Sum: " + validGameIdSum);
    }

    @Override
    public void doTwoStarSolution() {
        int powerOfSetsSum = 0;
        for (CubeGame cubeGame : cubeGames) {
            powerOfSetsSum += cubeGame.getPowerOfCubes();
        }
        System.out.println("Power of Sets Sum: " + powerOfSetsSum);
    }

}
