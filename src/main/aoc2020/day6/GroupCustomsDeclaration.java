package aoc2020.day6;

import java.util.HashMap;
import java.util.Map;

public class GroupCustomsDeclaration
{
    private final Map<Character, Integer> yesAnswersCount = new HashMap<>();
    private int numberOfGroupMembers = 0;

    GroupCustomsDeclaration(String groupAnswers)
    {
        String[] answersPerPerson = groupAnswers.split("\\n");
        for (String answerPerPerson : answersPerPerson)
        {
            for (int i = 0; i < answerPerPerson.length(); i++)
            {
                char currChar = answerPerPerson.charAt(i);
                if (currChar != '\r')
                {
                    if (!yesAnswersCount.containsKey(currChar))
                    {
                        yesAnswersCount.put(currChar, 1);
                    }
                    else
                    {
                        yesAnswersCount.put(currChar, yesAnswersCount.get(currChar) + 1);
                    }
                }
            }
            numberOfGroupMembers++;
        }
    }

    int getYesAnswers()
    {
        return yesAnswersCount.size();
    }

    int getCommonYesAnswers()
    {
        return yesAnswersCount.values().stream().filter(x -> x == numberOfGroupMembers).toList().size();
    }
}
