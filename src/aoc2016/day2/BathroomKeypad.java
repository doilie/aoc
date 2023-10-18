package aoc2016.day2;

import java.util.Hashtable;

public class BathroomKeypad {
    private final Hashtable<String, String> numberPositions = new Hashtable<>();
    private final KeypadPosition currentPosition;

    public BathroomKeypad(KeypadPosition currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void addButton(KeypadPosition position, String buttonLabel) {
        numberPositions.put(position.getName(), buttonLabel);
    }

    public KeypadPosition getCurrentPosition() {
        return currentPosition;
    }

    public String getKeypadNumberAtCurrentPosition() {
        return numberPositions.get(currentPosition.getName());
    }

    public boolean doesButtonExist(String positionCoordinates) {
        return numberPositions.get(positionCoordinates) != null;
    }

    public static BathroomKeypad getSimpleKeypad() {
        BathroomKeypad simpleKeypad = new BathroomKeypad(new KeypadPosition(2, 2));
        for (int i = 1; i <= 9; i++) {
            KeypadPosition numberPosition = new KeypadPosition((i - 1) % 3, (i - 1) / 3);
            simpleKeypad.addButton(numberPosition, Integer.toString(i));
        }
        return simpleKeypad;
    }

    public static BathroomKeypad getFancyKeypad() {
        BathroomKeypad fancyKeypad = new BathroomKeypad(new KeypadPosition(0, 2));
        fancyKeypad.addButton(new KeypadPosition(2, 0), "1");
        fancyKeypad.addButton(new KeypadPosition(1, 1), "2");
        fancyKeypad.addButton(new KeypadPosition(2, 1), "3");
        fancyKeypad.addButton(new KeypadPosition(3, 1), "4");
        fancyKeypad.addButton(new KeypadPosition(0, 2), "5");
        fancyKeypad.addButton(new KeypadPosition(1, 2), "6");
        fancyKeypad.addButton(new KeypadPosition(2, 2), "7");
        fancyKeypad.addButton(new KeypadPosition(3, 2), "8");
        fancyKeypad.addButton(new KeypadPosition(4, 2), "9");
        fancyKeypad.addButton(new KeypadPosition(1, 3), "A");
        fancyKeypad.addButton(new KeypadPosition(2, 3), "B");
        fancyKeypad.addButton(new KeypadPosition(3, 3), "C");
        fancyKeypad.addButton(new KeypadPosition(2, 4), "D");
        return fancyKeypad;
    }

}
