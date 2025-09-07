package aoc2022.day22;

import java.util.ArrayList;
import java.util.List;

public class MonkeyTileMove {
    public int getSteps() {
        return steps;
    }

    public Turn getTurn() {
        return turn;
    }

    public enum Turn {
        Clockwise,
        Counterclockwise,
        None
    }

    private final int steps;
    private Turn turn;

    public MonkeyTileMove(int steps, String turn) {
        this.steps = steps;
        if (List.of("R", "L").contains(turn)) {
            switch (turn) {
                case "R":
                    this.turn = Turn.Clockwise;
                    break;
                case "L":
                    this.turn = Turn.Counterclockwise;
                    break;
            }
        }
        else {
            this.turn = Turn.None;
        }
    }

    public static List<MonkeyTileMove> parseMoves(String moves) {
        List<MonkeyTileMove> monkeyTileMoves = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < moves.length(); i++) {
            char currChar = moves.charAt(i);
            if (currChar == 'R' || currChar == 'L') {
                int steps = Integer.parseInt(sb.toString());
                String turn = Character.toString(currChar);
                sb.delete(0, sb.length());
                monkeyTileMoves.add(new MonkeyTileMove(steps, turn));
            }
            else {
                sb.append(currChar);
            }
            if (i == moves.length() - 1) {
                int steps = Integer.parseInt(sb.toString());
                monkeyTileMoves.add(new MonkeyTileMove(steps, ""));
            }
        }
        return monkeyTileMoves;
    }
}
