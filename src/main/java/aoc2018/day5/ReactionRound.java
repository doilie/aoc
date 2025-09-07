package aoc2018.day5;

import java.util.ArrayList;
import java.util.List;

public class ReactionRound
{
    private static final List<String> REACTING_PAIRS = new ArrayList<>();
    private static final String ALL_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";

    static {


        for (int i = 0; i < ALL_LOWERCASE.length(); i++)
        {
            String uppercaseChar = ALL_LOWERCASE.substring(i, i + 1);
            String lowercaseChar = uppercaseChar.toUpperCase();
            REACTING_PAIRS.add(uppercaseChar + lowercaseChar);
            REACTING_PAIRS.add(lowercaseChar + uppercaseChar);
        }
    }

    public static String react(String polymer)
    {
        String resultString = polymer;
        for (String reactingPair : REACTING_PAIRS) {
            resultString = resultString.replace(reactingPair, "");
        }

        return resultString;
    }

    public static String reactContinuously(String polymer)
    {
        String reactionResult = polymer;
        int origLength = 0;
        int newLength = -1;

        while (newLength != 0 && origLength != newLength)
        {
            origLength = reactionResult.length();
            reactionResult = ReactionRound.react(reactionResult);
            newLength = reactionResult.length();
        }

        return reactionResult;
    }

    public static String reactContinuouslyWithoutUnit(String polymer, String unit)
    {
        unit = unit.toLowerCase();
        String reactionResult = polymer.replace(unit, "");
        reactionResult = reactionResult.replace(unit.toUpperCase(), "");
        return reactContinuously(reactionResult);
    }

    public static String getShortestPolymerFromRemovingUnit(String polymer)
    {
        String shortestPolymer = polymer;
        for (int i = 0; i < ALL_LOWERCASE.length(); i++)
        {
            String lowercaseChar = ALL_LOWERCASE.substring(i, i + 1);
            String reactionResult = reactContinuouslyWithoutUnit(polymer, lowercaseChar);
            if (reactionResult.length() < shortestPolymer.length())
            {
                shortestPolymer = reactionResult;
            }
        }
        return shortestPolymer;
    }
}
