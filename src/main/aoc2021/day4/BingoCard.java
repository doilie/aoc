package aoc2021.day4;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BingoCard {
    private static String getPosition(int x, int y) {
        return x + "," + y;
    }
    private static final int SIZE = 5;
    private final Map<Integer, String> cardNumbers = new HashMap<>();
    private final Map<String, Boolean> markedPositions = new HashMap<>();

    public BingoCard(String bingoCardString) {
        String[] lines = bingoCardString.split("\\n");
        int x;
        int y = 0;
        for (String line : lines) {
            x = 0;
            String[] numbers = line.split("\\s+");
            for (String number : numbers) {
                String position = getPosition(x, y);
                if (!number.trim().isBlank()) {
                    cardNumbers.put(Integer.parseInt(number.trim()), position);
                    markedPositions.put(position, false);
                    x++;
                }
            }
            y++;
        }
    }

    public void markNumber(int number) {
        if (cardNumbers.containsKey(number)) {
            markedPositions.put(cardNumbers.get(number), true);
        }
    }

    public boolean isPositionMarked(String position) {
        return markedPositions.get(position);
    }

    public boolean hasWon()
    {
        for (int x = 0; x < SIZE; x++)
        {
            boolean rowWon = true;
            for (int y = 0; y < SIZE; y++)
            {
                String rowPosition = getPosition(x, y);
                if (!markedPositions.get(rowPosition))
                {
                    rowWon = false;
                    break;
                }
            }
            if (rowWon)
            {
                return true;
            }
        }

        for (int y = 0; y < SIZE; y++)
        {
            boolean colWon = true;
            for (int x = 0; x < SIZE; x++)
            {
                String colPosition = getPosition(x, y);
                if (!markedPositions.get(colPosition))
                {
                    colWon = false;
                    break;
                }
            }
            if (colWon)
            {
                return true;
            }
        }
        return false;
    }

    public int computeScore(int winningNumber)
    {
        Set<String> unmarkedPositions = markedPositions.entrySet().stream().filter(kv -> !kv.getValue()).map(Map.Entry::getKey).collect(Collectors.toSet());
        int unmarkedPositionsSum = cardNumbers.entrySet().stream().filter(kv -> unmarkedPositions.contains(kv.getValue())).map(Map.Entry::getKey).mapToInt(Integer::intValue).sum();
        return unmarkedPositionsSum * winningNumber;
    }
}
