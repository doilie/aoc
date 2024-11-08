package aoc2021.day3;

import java.util.ArrayList;
import java.util.List;

public class BinaryDiagnostic
{
    private final List<BinaryNumber> binaryNumberList = new ArrayList<>();

    public BinaryDiagnostic(String input)
    {
        String[] lines = input.split("\\n");
        List<String> tempStringList = new ArrayList<>();
        for (int l = 0; l < lines.length; l++)
        {
            String line = lines[l];
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

}
