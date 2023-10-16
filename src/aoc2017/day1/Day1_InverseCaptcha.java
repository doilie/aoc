package aoc2017.day1;

import lib.Challenge;

public class Day1_InverseCaptcha extends Challenge {
    public static void main(String[] args) {
        Day1_InverseCaptcha day1 = new Day1_InverseCaptcha();
        day1.doOneStarSolution();
        day1.doTwoStarSolution();
    }

    public Day1_InverseCaptcha() {
        super("2017/day1-input.txt");
        this.parseFile();
    }

    private final CaptchaDigits captchaDigits = new CaptchaDigits();

    @Override
    protected void parseFile() {
        for (int i = 0; i < getFileContents().length() - 1; i++) {
            captchaDigits.addDigit(Integer.parseInt(Character.toString(getFileContents().charAt(i))));
        }
    }

    @Override
    public void doOneStarSolution() {
        System.out.println("Captcha solution 1: " + captchaDigits.getSolution1());
    }

    @Override
    public void doTwoStarSolution() {
        System.out.println("Captcha solution 2: " + captchaDigits.getSolution2());
    }
}
