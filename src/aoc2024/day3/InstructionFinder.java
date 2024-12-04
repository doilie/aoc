package aoc2024.day3;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstructionFinder
{
    private final List<String> instructions = new ArrayList<>();

    public InstructionFinder(String input, String regex)
    {
        parseInstructions(input, regex);
    }

    public InstructionFinder(String input, String regex, String enabler, String disabler)
    {
        String newInput = getEnabledInstructions(input, enabler, disabler);
        parseInstructions(newInput, regex);
    }

    public List<String> getInstructions()
    {
        return instructions;
    }

    private String getEnabledInstructions(String input, String enabler, String disabler)
    {
        String[] splitInstructions = input.split(disabler);
        StringBuilder enabledInstructions = new StringBuilder();
        if (splitInstructions.length > 0)
        {
            enabledInstructions.append(splitInstructions[0]);
            for (int i = 1; i < splitInstructions.length; i++)
            {
                String instruction = splitInstructions[i];
                int enabledIdx = instruction.indexOf(enabler);
                if (enabledIdx != -1)
                {
                    enabledInstructions.append(instruction.substring(enabledIdx + enabler.length()));
                }
            }

        }
        return enabledInstructions.toString();
    }

    private void parseInstructions(String input, String regex)
    {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        while(m.find())
        {
            instructions.add(m.group());
        }
    }
}
