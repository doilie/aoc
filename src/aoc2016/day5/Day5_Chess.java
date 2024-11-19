package aoc2016.day5;

import lib.Challenge;

public class Day5_Chess extends Challenge {
    public static void main(String[] args) {
        Day5_Chess day5 = new Day5_Chess();
        day5.doOneStarSolution();
        day5.doTwoStarSolution();
    }

    public Day5_Chess() {
        super("2016/day5-input.txt");
        this.parseFile();
    }

    @Override
    protected void parseFile() {
    }

    @Override
    public void doOneStarSolution() {
        MD5Password md5Password = new MD5Password(getFileContents());
        System.out.println("Door password: " + md5Password.crackPassword(8, 5));
    }

    @Override
    public void doTwoStarSolution() {
        MD5Password md5Password = new MD5Password(getFileContents());
        System.out.println("Door password cinematic: " + md5Password.crackPassword_v2(8, 5));
    }
}
