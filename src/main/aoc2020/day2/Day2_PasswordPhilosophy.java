package aoc2020.day2;

import lib.Challenge;

public class Day2_PasswordPhilosophy extends Challenge {
    public static void main(String[] args) {
        Day2_PasswordPhilosophy day2 = new Day2_PasswordPhilosophy();
        day2.doOneStarSolution();
        day2.doTwoStarSolution();
    }

    public Day2_PasswordPhilosophy() {
        super("2020/day2-input.txt");
        this.parseFile();
    }

    private final PasswordChecker passwordChecker = new PasswordChecker();

    @Override
    protected void parseFile() {
        String[] lines = this.getFileContents().split("\n");
        for (String line : lines) {
            passwordChecker.addPassword(line);
        }
    }

    @Override
    public void doOneStarSolution() {
        passwordChecker.setPasswordPolicyCountChars();
        System.out.println("Valid password count (count chars): " + passwordChecker.countValidPasswords());
    }

    @Override
    public void doTwoStarSolution() {
        passwordChecker.setPasswordPolicyCharPosition();
        System.out.println("Valid password count (char position): " + passwordChecker.countValidPasswords());
    }
}
