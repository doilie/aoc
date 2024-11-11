package aoc2015.day4;

import lib.Challenge;

public class Day4_StockingStuffer extends Challenge {
    public static void main(String[] args) {
        Day4_StockingStuffer day4 = new Day4_StockingStuffer();
        day4.doOneStarSolution();
        day4.doTwoStarSolution();
    }

    public Day4_StockingStuffer() {
        super("2015/day4-input.txt");
        this.parseFile();
    }

    @Override
    protected void parseFile() {
    }

    @Override
    public void doOneStarSolution() {
        MD5HashGenerator md5HashGenerator = new MD5HashGenerator(getFileContents());
        System.out.println("AdventCoin Generator Decimal Part 5 zeroes: " + md5HashGenerator.generateSecretKeyDecimalPart(5));
    }

    @Override
    public void doTwoStarSolution() {
        MD5HashGenerator md5HashGenerator = new MD5HashGenerator(getFileContents());
        System.out.println("AdventCoin Generator Decimal Part 6 zeroes: " + md5HashGenerator.generateSecretKeyDecimalPart(6));
    }
}
