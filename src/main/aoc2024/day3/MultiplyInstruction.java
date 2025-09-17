package aoc2024.day3;

public class MultiplyInstruction
{
    private static final String MULTIPLY = "mul";
    private long multiplicand;
    private long multiplier;
    public MultiplyInstruction(String input)
    {
        if (input.startsWith(MULTIPLY) && input.indexOf("(") < input.indexOf(")"))
        {
            String numberPart = input.substring(input.indexOf("(") + 1, input.indexOf(")"));
            String[] numbers = numberPart.split(",");
            if (numbers.length == 2)
            {
                multiplicand = Integer.parseInt(numbers[0]);
                multiplier = Integer.parseInt(numbers[1]);
            }
        }
    }

    public long getResult()
    {
        return multiplicand * multiplier;
    }
}
