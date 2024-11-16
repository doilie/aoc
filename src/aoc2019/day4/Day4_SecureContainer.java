package aoc2019.day4;

import lib.Challenge;

public class Day4_SecureContainer extends Challenge {
    public static void main(String[] args) {
        Day4_SecureContainer day4 = new Day4_SecureContainer();
        day4.doOneStarSolution();
        day4.doTwoStarSolution();
    }

    public Day4_SecureContainer() {
        super("2019/day4-input.txt");
        this.parseFile();
    }

    private int startNum;
    private int endNum;

    @Override
    protected void parseFile() {
        String[] numbers = this.getFileContents().split("-");
        if (numbers.length == 2)
        {
            startNum = Integer.parseInt(numbers[0]);
            endNum = Integer.parseInt(numbers[1]);
        }
    }

    @Override
    public void doOneStarSolution() {
        PasswordChecker pwc = new PasswordChecker();
        int ctr = 0;
        for (int num = startNum; num <= endNum; num++)
        {
            if (pwc.isValid(String.valueOf(num)))
            {
                ctr++;
            }
        }
        System.out.println("Number of valid passwords in range: " + ctr);
    }

    @Override
    public void doTwoStarSolution() {

    }
}
