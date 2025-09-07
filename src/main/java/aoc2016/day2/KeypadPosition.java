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

    public void moveUp(BathroomKeypad keypad) {
        if (keypad.doesButtonExist(buildName(x, y - 1))) {
            this.y--;
        }
    }

    public void moveDown(BathroomKeypad keypad) {
        if (keypad.doesButtonExist(buildName(x, y + 1))) {
            this.y++;
        }
    }

    public void moveLeft(BathroomKeypad keypad) {
        if (keypad.doesButtonExist(buildName(x - 1, y))) {
            this.x--;
        }
    }

    public void moveRight(BathroomKeypad keypad) {
        if (keypad.doesButtonExist(buildName(x + 1, y))) {
            this.x++;
        }
    }

    public static String buildName(int x, int y) {
        return x + "," + y;
    }
}
