package aoc2021.day3;

import java.util.ArrayList;
import java.util.List;

public class BinaryDiagnostic
{
    private final List<BinaryNumber> binaryNumberList = new ArrayList<>();
    private final List<String> inputList = new ArrayList<>();

    public static List<String> getEntriesWithBitAtPosition(List<String> input, char bit, int position)
    {
        return input.stream().filter(val -> val.charAt(position) == bit).toList();
    }

    public static String convertInputListToString(List<String> inputList)
    {
        StringBuilder sb = new StringBuilder();
        for (String line : inputList)
        {
            sb.append(line);
            sb.append("\n");
        }
        return sb.toString();
    }

    public BinaryDiagnostic(String input)
    {
        inputList.addAll(List.of(input.split("\\n")));
        List<String> tempStringList = new ArrayList<>();
        for (int l = 0; l < inputList.size(); l++)
        {
            String line = inputList.get(l);
            for (int i = 0; i < line.length(); i++)
            {
                if (l == 0)
                {
                    tempStringList.add(String.valueOf(line.charAt(i)));
                }
                else
                {
                    tempStringList.set(i, tempStringList.get(i) + line.charAt(i));
                }
            }
        }
        binaryNumberList.addAll(tempStringList.stream().map(BinaryNumber::new).toList());
    }

    public String getGammaRate()
    {
        StringBuilder sb = new StringBuilder();
        for (BinaryNumber b : binaryNumberList)
        {
            sb.append(b.getMostCommonBit());
        }
        return sb.toString();
    }

    public String getEpsilonRate()
    {
        StringBuilder sb = new StringBuilder();
        for (BinaryNumber b : binaryNumberList)
        {
            sb.append(b.getLeastCommonBit());
        }
        return sb.toString();
    }

    public String getOxygenGeneratorRating()
    {
        BinaryDiagnostic currObj = this;

        int position = 0;
        while (currObj.inputList.size() > 1)
        {
            String gammaRate = currObj.getGammaRate();
            List<String> nextEntries = getEntriesWithBitAtPosition(currObj.inputList, gammaRate.charAt(position), position);
            currObj = new BinaryDiagnostic(convertInputListToString(nextEntries));
            position++;
        }

        return currObj.inputList.get(0);
    }


    public String getCO2ScrubberRating()
    {
        BinaryDiagnostic currObj = this;

        int position = 0;
        while (currObj.inputList.size() > 1)
        {
            String epsilonRate = currObj.getEpsilonRate();
            List<String> nextEntries = getEntriesWithBitAtPosition(currObj.inputList, epsilonRate.charAt(position), position);
            currObj = new BinaryDiagnostic(convertInputListToString(nextEntries));
            position++;
        }

        return currObj.inputList.get(0);
    }

}
