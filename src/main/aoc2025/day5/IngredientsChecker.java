package aoc2025.day5;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class IngredientsChecker {
    private final Map<Long, Long> freshIngredientsRanges = new HashMap<>();

    IngredientsChecker(String rangeLines) {
        String[] lines =  rangeLines.split("\n");

        for (String line : lines)
        {
            String[] rangeStartEnd = line.split("-");
            long newStart = Long.parseLong(rangeStartEnd[0]);
            long newEnd = Long.parseLong(rangeStartEnd[1]);

            // check if there are ranges that are within the start and end
            Set<Long> rangesWithinNewRange = freshIngredientsRanges.entrySet().stream()
                    .filter(entry -> newStart < entry.getKey() && newEnd > entry.getValue()).map(Map.Entry::getKey).collect(Collectors.toSet());
            if (!rangesWithinNewRange.isEmpty())
            {
                for (Long range : rangesWithinNewRange)
                {
                    freshIngredientsRanges.remove(range);
                    freshIngredientsRanges.put(newStart, newEnd);
                }
                continue;
            }

            // get which range new start is in
            Map.Entry<Long, Long> newStartRange = freshIngredientsRanges.entrySet().stream()
                    .filter(entry -> entry.getKey() <= newStart && newStart <= entry.getValue()).findFirst().orElse(null);
            // get which range new end is in
            Map.Entry<Long, Long> newEndRange = freshIngredientsRanges.entrySet().stream()
                    .filter(entry -> entry.getKey() <= newEnd && newEnd <= entry.getValue()).findFirst().orElse(null);

            if (newStartRange == null && newEndRange == null)
            {
                freshIngredientsRanges.put(newStart, newEnd);
            }
            else if (newStartRange != null & newEndRange != null)
            {
                if (!Objects.equals(newStartRange.getKey(), newEndRange.getKey()))
                {
                    long newEndRangeValue = newEndRange.getValue();
                    freshIngredientsRanges.put(newStartRange.getKey(), newEndRangeValue);
                    freshIngredientsRanges.remove(newEndRange.getKey());
                }
            }
            else if (newStartRange != null)
            {
                long newStartRangeEnd = newStartRange.getValue();
                if (newEnd > newStartRangeEnd)
                {
                    freshIngredientsRanges.put(newStartRange.getKey(), newEnd);
                }
            }
            else
            {
                long newEndRangeStart = newEndRange.getKey();
                if (newStart < newEndRangeStart)
                {
                    freshIngredientsRanges.put(newStart, newEndRange.getValue());
                    freshIngredientsRanges.remove(newEndRangeStart);
                }
            }
        }
    }

    Map<Long, Long> getFreshIngredientsRanges()
    {
        return freshIngredientsRanges;
    }

    boolean isFreshIngredient(long ingredient)
    {
        Map.Entry<Long, Long> ingredientRange = freshIngredientsRanges.entrySet().stream()
            .filter(entry -> entry.getKey() <= ingredient && ingredient <= entry.getValue()).findFirst().orElse(null);
        return ingredientRange != null;
    }

    int countFreshIngredientsInList(String ingredientIdList)
    {
        int counter = 0;
        String[] ingredientIds  = ingredientIdList.split("\\n");
        for (String ingredient : ingredientIds)
        {
            if (!ingredient.isEmpty())
            {
                if (isFreshIngredient(Long.parseLong(ingredient.trim())))
                {
                    counter++;
                }
            }
        }
        return counter;
    }

    long countFreshIngredientsInRanges()
    {
        return freshIngredientsRanges.entrySet().stream().map(entry -> entry.getValue() - entry.getKey() + 1).mapToLong(Long::longValue).sum();
    }
}
