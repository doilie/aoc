package aoc2018.day2;

import lib.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Day2_Inventory extends Challenge {
    public static void main(String[] args) {
        Day2_Inventory day2 = new Day2_Inventory();
        day2.doOneStarSolution();
        day2.doTwoStarSolution();
    }

    public Day2_Inventory() {
        super("2018/day2-input.txt");
        this.parseFile();
    }

    private final List<CandidateBox> candidateBoxList = new ArrayList<>();

    @Override
    protected void parseFile() {
        String[] lines = this.getFileContents().split("\n");
        for (String line : lines) {
            candidateBoxList.add(new CandidateBox(line));
        }
    }

    @Override
    public void doOneStarSolution() {
        int twoLetterCount = (int) candidateBoxList.stream().filter(candidateBox -> candidateBox.containsLetterCount((short) 2)).count();
        int threeLetterCount = (int) candidateBoxList.stream().filter(candidateBox -> candidateBox.containsLetterCount((short) 3)).count();
        System.out.println("Checksum using two and three letter count: " + (twoLetterCount * threeLetterCount));
    }

    @Override
    public void doTwoStarSolution() {
        String commonLetters = "";
        boolean foundOneDiff = false;
        for (int i = 0; i < candidateBoxList.size(); i++) {
            CandidateBox candidateBox = candidateBoxList.get(i);
            for (int j = i; j < candidateBoxList.size(); j++) {
                CandidateBox toCompare = candidateBoxList.get(j);
                commonLetters = candidateBox.getCommonLetters(toCompare);
                if (commonLetters.length() == candidateBox.getTotalLetters() - 1) {
//                    System.out.println(i + "," + j);
                    foundOneDiff = true;
                    break;
                }
            }
            if (foundOneDiff) {
                break;
            }
        }
        System.out.println("Common letters for strings with only 1 diff: " + commonLetters);
    }
}
