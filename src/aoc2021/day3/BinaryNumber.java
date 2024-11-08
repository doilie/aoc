package aoc2021.day3;

public class BinaryNumber {
    private final String binaryNumber;
    private int numberOfZeroes;
    private int numberOfOnes;

    public BinaryNumber(String binaryNumber)
    {
        this.binaryNumber = binaryNumber;
        countNumberOfBits();
    }

    private void countNumberOfBits()
    {
        for (int i = 0; i < this.binaryNumber.length(); i++)
        {
            if (isBitZero(i))
            {
                numberOfZeroes++;
            }
            else if (isBitOne(i))
            {
                numberOfOnes++;
            }
        }
    }

    private boolean isBitZero(int bitPosition)
    {
        return this.binaryNumber.charAt(bitPosition) == '0';
    }

    private boolean isBitOne(int bitPosition)
    {
        return this.binaryNumber.charAt(bitPosition) == '1';
    }


    public int getMostCommonBit()
    {
        return numberOfZeroes > numberOfOnes ? 0 : 1;
    }

    public int getLeastCommonBit()
    {
        return numberOfZeroes < numberOfOnes ? 0 : 1;
    }

    public long convertToDecimal()
    {
        long decimalValue = 0;
        for (int i = 0; i < this.binaryNumber.length(); i++)
        {
            int bitPosition = this.binaryNumber.length() - 1 - i;
            if (isBitOne(bitPosition))
            {
                decimalValue += (long) Math.pow(2, i);
            }
        }
        return decimalValue;
    }
}
