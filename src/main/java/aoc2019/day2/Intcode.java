package aoc2019.day2;

import java.util.List;

public class Intcode {
    public static void runProgram(List<Integer> codes, int noun, int verb) {
        codes.set(1, noun);
        codes.set(2, verb);
        for (int i = 0; i < codes.size(); i+=4) {
            int opCode = codes.get(i);
            int firstNum = codes.get(codes.get(i + 1));
            int secondNum = codes.get(codes.get(i + 2));
            int resultIdx = codes.get(i + 3);
            switch(opCode) {
                case 1: codes.set(resultIdx, firstNum + secondNum); break;
                case 2: codes.set(resultIdx, firstNum * secondNum); break;
                case 99: return;
            }
        }
    }
}
