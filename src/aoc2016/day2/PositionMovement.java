package aoc2016.day2;

import java.util.ArrayList;
import java.util.List;

public class PositionMovement {
    public enum MoveDirection {
        Up,
        Down,
        Left,
        Right
    }
    private final List<MoveDirection> moveDirections = new ArrayList<>();

    public PositionMovement(String input) {
        for (int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
            switch(currChar) {
                case 'U': moveDirections.add(MoveDirection.Up); break;
                case 'D': moveDirections.add(MoveDirection.Down); break;
                case 'L': moveDirections.add(MoveDirection.Left); break;
                case 'R': moveDirections.add(MoveDirection.Right); break;
            }
        }
    }

    public void applyMovement(BathroomKeypad keypad) {
        KeypadPosition currentPosition = keypad.getCurrentPosition();
        for (PositionMovement.MoveDirection direction : moveDirections) {
            switch(direction) {
                case Up:
                    if (currentPosition.canMoveUp(keypad)) {
                        currentPosition.moveUp();
                    }
                    break;
                case Down:
                    if (currentPosition.canMoveDown(keypad)) {
                        currentPosition.moveDown();
                    }
                    break;
                case Left:
                    if (currentPosition.canMoveLeft(keypad)) {
                        currentPosition.moveLeft();
                    }
                    break;
                case Right:
                    if (currentPosition.canMoveRight(keypad)) {
                        currentPosition.moveRight();
                    }
                    break;
            }
        }
    }
}
