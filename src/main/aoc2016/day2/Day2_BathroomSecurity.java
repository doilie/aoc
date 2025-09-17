package aoc2016.day2;

import lib.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Day2_BathroomSecurity extends Challenge {
    public static void main(String[] args) {
        Day2_BathroomSecurity day2 = new Day2_BathroomSecurity();
        day2.doOneStarSolution();
        day2.doTwoStarSolution();
    }

    public Day2_BathroomSecurity() {
        super("2016/day2-input.txt");
        this.parseFile();
    }

    private final List<PositionMovement> positionMovementList = new ArrayList<>();

    @Override
    protected void parseFile() {
        String[] movements = getFileContents().split("\\n");
        for (String movement : movements) {
            positionMovementList.add(new PositionMovement(movement));
        }
    }

    @Override
    public void doOneStarSolution() {
        BathroomKeypad bathroomKeypad = BathroomKeypad.getSimpleKeypad();
        List<String> bathroomCode = new ArrayList<>();
        for (PositionMovement positionMovement : positionMovementList) {
            positionMovement.applyMovement(bathroomKeypad);
            bathroomCode.add(bathroomKeypad.getKeypadNumberAtCurrentPosition());
        }
        System.out.println("Bathroom code for simple keypad: " + String.join("", bathroomCode));
    }

    @Override
    public void doTwoStarSolution() {
        BathroomKeypad bathroomKeypad = BathroomKeypad.getFancyKeypad();
        List<String> bathroomCode = new ArrayList<>();
        for (PositionMovement positionMovement : positionMovementList) {
            positionMovement.applyMovement(bathroomKeypad);
            bathroomCode.add(bathroomKeypad.getKeypadNumberAtCurrentPosition());
        }
        System.out.println("Bathroom code for fancy keypad: " + String.join("", bathroomCode));
    }
}
