package aoc2025.day1;

public class SafeDial
{
    private static final String LEFT = "L";
    private static final String RIGHT = "R";
    private static final int MAX_NUM = 100;

    private int currentPosition;

    SafeDial(int currentPosition)
    {
        this.currentPosition = currentPosition;
    }

    void turn(String instruction)
    {
        String direction = getDirection(instruction);
        int steps = getSteps(instruction);
        switch (direction)
        {
            case LEFT:
            {
                currentPosition = ((currentPosition - steps) + MAX_NUM) % MAX_NUM;
                break;
            }
            case RIGHT:
            {
                currentPosition = ((currentPosition + steps) + MAX_NUM) % MAX_NUM;
                break;
            }
        }
    }

    int getCurrentPosition()
    {
        return currentPosition;
    }

    private static String getDirection(String direction)
    {
        return direction.substring(0, 1);
    }

    private static int getSteps(String direction)
    {
        return Integer.parseInt(direction.substring(1));
    }
}
