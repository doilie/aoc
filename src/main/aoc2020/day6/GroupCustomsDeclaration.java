package aoc2020.day6;

import java.util.HashMap;
import java.util.Map;

public class GroupCustomsDeclaration
{
    private final Map<Character, Integer> yesAnswersCount = new HashMap<>();

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
                    yesAnswersCount.put(currChar, yesAnswersCount.getOrDefault(currChar, 1));
                }
            }
        }
    }

    int getYesAnswers()
    {
        return yesAnswersCount.size();
    }
}
