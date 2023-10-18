package aoc2016.day2;

public class KeypadPosition {
    private int x;
    private int y;

    public KeypadPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return buildName(x, y);
    }

    public void moveUp() {
        this.y--;
    }

    public boolean canMoveUp(BathroomKeypad keypad) {
        return keypad.doesButtonExist(buildName(x, y - 1));
    }

    public void moveDown() {
        this.y++;
    }

    public boolean canMoveDown(BathroomKeypad keypad) {
        return keypad.doesButtonExist(buildName(x, y + 1));
    }

    public void moveLeft() {
        this.x--;
    }

    public boolean canMoveLeft(BathroomKeypad keypad) {
        return keypad.doesButtonExist(buildName(x - 1, y));
    }

    public void moveRight() {
        this.x++;
    }

    public boolean canMoveRight(BathroomKeypad keypad) {
        return keypad.doesButtonExist(buildName(x + 1, y));
    }

    public static String buildName(int x, int y) {
        return x + "," + y;
    }
}
