package aoc2017.day1;

import java.util.ArrayList;
import java.util.List;

public class CaptchaDigits {
    private final List<Integer> digits = new ArrayList<>();

    public void addDigit(int digit) {
        digits.add(digit);
    }

    public int getSolution1() {
        int solution = 0;
        for (int i = 0; i < digits.size(); i++) {
            int num1 = digits.get(i);
            int num2 = i != digits.size() - 1 ? digits.get(i + 1) : digits.get(0);
            if (num1 == num2) {
                solution += num1;
            }
        }
        return solution;
    }

    public int getSolution2() {
        int solution = 0;
        int half = digits.size() / 2;
        for (int i = 0; i < half; i++) {
            int num1 = digits.get(i);
            int num2 = digits.get(half + i);
            if (num1 == num2) {
                solution += num1;
            }
        }
        return solution * 2;
    }
}
