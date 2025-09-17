package aoc2017.day5;

import java.util.ArrayList;
import java.util.List;

public class Trampoline
{
    private final List<Integer> instructions = new ArrayList<>();

    public Trampoline(String instructions)
    {
        String[] tokens = instructions.split("\\n");
        for (String token : tokens)
        {
            this.instructions.add(Integer.parseInt(token));
        }
    }

    public int escapeMaze()
    {
        int numInstructions = 0;
        List<Integer> editedInstructions = new ArrayList<>(instructions);

        int currentIndex = 0;
        while (currentIndex < editedInstructions.size())
        {
            int idxToAdd = editedInstructions.get(currentIndex);
            editedInstructions.set(currentIndex, editedInstructions.get(currentIndex) + 1);
            currentIndex += idxToAdd;
            numInstructions++;
        }

        return numInstructions;
    }

    public int escapeMaze_v2()
    {
        int numInstructions = 0;
        List<Integer> editedInstructions = new ArrayList<>(instructions);

        int currentIndex = 0;
        while (currentIndex < editedInstructions.size())
        {
            int idxToAdd = editedInstructions.get(currentIndex);
            if (idxToAdd >= 3)
            {
                editedInstructions.set(currentIndex, editedInstructions.get(currentIndex) - 1);
            }
            else {
                editedInstructions.set(currentIndex, editedInstructions.get(currentIndex) + 1);
            }
            currentIndex += idxToAdd;
            numInstructions++;
        }

        return numInstructions;
    }
}
