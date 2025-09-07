package aoc2017.day3;

public class SpiralSteps {
    public int getSteps(long inputValue)
    {
        int nearestSquareRoot = (int) getSquareRoot(inputValue);
        if (nearestSquareRoot % 2 == 0)
        {
            nearestSquareRoot--;
        }
        long startingPoint = (long) nearestSquareRoot * nearestSquareRoot;
        int x = nearestSquareRoot / 2;
        int y = nearestSquareRoot / 2;
        if (startingPoint != inputValue)
        {
            x++;
            y++;
            int state = 1;
            while (startingPoint != inputValue)
            {
                startingPoint++;
                switch (state)
                {
                    case 1: // up
                        y--;
                        break;
                    case 2: // left
                        x--;
                        break;
                    case 3: // down
                        y++;
                        break;
                    case 4: // right
                        x++;
                        break;
                }

                if (Math.abs(x) == Math.abs(y))
                {
                    state++;  // turn when at edge of square
                }
            }
        }

        return Math.abs(x) + Math.abs(y);
    }

    private long getSquareRoot(long inputValue)
    {
        return (long) Math.sqrt(inputValue);
    }
}
