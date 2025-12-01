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
                currentPosition = (currentPosition - steps) % MAX_NUM;
                if (currentPosition < 0)
                {
                    currentPosition = currentPosition + MAX_NUM;
                }
                break;
            }
            case RIGHT:
            {
                currentPosition = (currentPosition + steps) % MAX_NUM;
                break;
            }
        }
    }

    int getPassword(String[] instructions)
    {
        int password = 0;
        for (String instruction : instructions)
        {
            if (instruction.isEmpty())
            {
                continue;
            }
            password += getDialPointsToZero(instruction);
            turn(instruction);
        }
        return password;
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
        return Integer.parseInt(direction.substring(1).trim());
    }

    int getDialPointsToZero(String instruction)
    {
        String direction = getDirection(instruction);
        int steps = getSteps(instruction);
        switch (direction)
        {
            case LEFT:
            {
                if ((currentPosition - steps) <= 0)
                {
                    int toAdd = 1;
                    if (currentPosition == 0)
                    {
                        toAdd = 0;
                    }
                    int stepsRemaining = steps - currentPosition;  // 1st 0
                    return (stepsRemaining / MAX_NUM) + toAdd;
                }
                break;
            }
            case RIGHT:
            {
                if ((currentPosition + steps) >= MAX_NUM) {
                    int stepsRemaining = steps - (MAX_NUM - currentPosition);  // 1st 0
                    return (stepsRemaining / MAX_NUM) + 1;
                }
                break;
            }
        }
        return 0;
    }
}
