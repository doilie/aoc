package aoc2021.day3;

import lib.Challenge;

public class Day3_BinaryDiagnostic extends Challenge {
    public static void main(String[] args)  {
        Day3_BinaryDiagnostic day2 = new Day3_BinaryDiagnostic();
        day2.doOneStarSolution();
        day2.doTwoStarSolution();
    }


    public Day3_BinaryDiagnostic() {
        super("2021/day3-input.txt");
        this.parseFile();
    }

    @Override
    public void parseFile() {
    }

    @Override
    public void doOneStarSolution() {
        BinaryDiagnostic bd = new BinaryDiagnostic(this.getFileContents());
        BinaryNumber gammaRate = new BinaryNumber(bd.getGammaRate());
        BinaryNumber epsilonRate = new BinaryNumber(bd.getEpsilonRate());
        System.out.println("Power consumption of submarine: " + (gammaRate.convertToDecimal() * epsilonRate.convertToDecimal()));
    }

    @Override
    public void doTwoStarSolution() {
        BinaryDiagnostic bd = new BinaryDiagnostic(this.getFileContents());
        BinaryNumber oxygenGeneratorRating = new BinaryNumber(bd.getOxygenGeneratorRating());
        BinaryNumber co2ScrubberRating = new BinaryNumber(bd.getCO2ScrubberRating());
        System.out.println("Life support rating of submarine: " + (oxygenGeneratorRating.convertToDecimal() * co2ScrubberRating.convertToDecimal()));
    }
}
