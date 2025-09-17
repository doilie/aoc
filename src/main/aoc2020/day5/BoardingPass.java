package aoc2020.day5;

public class BoardingPass
{
    private final int NUMBER_OF_ROWS = 128;
    private final int NUMBER_OF_COLUMNS = 8;

    private final NumberFinder rowNumberFinder = new NumberFinder('F', 'B', NUMBER_OF_ROWS);
    private final NumberFinder columnNumberFinder = new NumberFinder('L', 'R', NUMBER_OF_COLUMNS);
    private final String input;

    public BoardingPass(String input)
    {
        this.input = input;
    }

    public int getSeatId()
    {
        int rowNumber = rowNumberFinder.find(input.substring(0, 8));
        int columnNumber = columnNumberFinder.find(input.substring(7));
        return rowNumber * NUMBER_OF_COLUMNS + columnNumber;
    }
}
