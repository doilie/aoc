package aoc2015.day1;

import java.util.ArrayList;
import java.util.List;

public class ParenthesisList {
    private final List<String> parenthesisList = new ArrayList<>();

    public void addParenthesis(String parenthesis) {
        this.parenthesisList.add(parenthesis);
    }

    public int getSantaCurrentFloor() {
        int openParenthesisCount = (int) parenthesisList.stream().filter(s -> s.equals("(")).count();
        int closeParenthesisCount = (int) parenthesisList.stream().filter(s -> s.equals(")")).count();
        return openParenthesisCount - closeParenthesisCount;
    }

    public int getPositionThatGoesToBasement() {
        int currentFloor = 0;
        int idx = 1;
        for (String parenthesis : parenthesisList) {
            if (parenthesis.equals("(")) {
                currentFloor++;
            }
            else if (parenthesis.equals(")")) {
                currentFloor--;
            }
            if (currentFloor == -1) {
                break;
            }
            idx++;
        }
        return idx;
    }
}
