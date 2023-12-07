package aoc2023.day7;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum CamelCardType {
    HighCard(1),
    OnePair(2),
    TwoPair(3),
    ThreeOfAKind(4),
    FullHouse(5),
    FourOfAKind(6),
    FiveOfAKind(7);

    private final int value;

    CamelCardType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CamelCardType getType(String cards) {
        return determineType(countCards(cards));
    }

    public static CamelCardType getTypeAsJoker(String cards) {
        HashMap<Character, Integer> cardCounter = countCards(cards);

        if (cardCounter.containsKey('J') && cardCounter.get('J') < 5) {
            int jokerCount = cardCounter.get('J');
            cardCounter.remove('J');
            Map.Entry<Character, Integer> maxEntrySet = getMaxEntrySet(cardCounter);
            cardCounter.put(maxEntrySet.getKey(), cardCounter.get(maxEntrySet.getKey()) + jokerCount);
        }

        return determineType(cardCounter);
    }

    private static CamelCardType determineType(HashMap<Character, Integer> cardCounter) {
        Set<Character> uniqueCards = cardCounter.keySet();
        if (uniqueCards.size() == 5) {
            return HighCard;
        }
        else if (uniqueCards.size() == 4) {
            return OnePair;
        }
        else if (uniqueCards.size() == 3) {
            if (getMaxEntrySet(cardCounter).getValue() == 2) {
                return TwoPair;
            }
            else {
                return ThreeOfAKind;
            }
        }
        else if (uniqueCards.size() == 2) {
            if (getMaxEntrySet(cardCounter).getValue() == 3) {
                return FullHouse;
            }
            else {
                return FourOfAKind;
            }
        }
        else {
            return FiveOfAKind;
        }
    }

    private static HashMap<Character, Integer> countCards(String cards) {
        HashMap<Character, Integer> cardCounter = new HashMap<>();
        for (int i = 0; i < cards.length(); i++) {
            char currentChar = cards.charAt(i);
            cardCounter.putIfAbsent(currentChar, 0);
            cardCounter.put(currentChar, cardCounter.get(currentChar) + 1);
        }
        return cardCounter;
    }

    private static Map.Entry<Character, Integer> getMaxEntrySet(HashMap<Character, Integer> cardCounter) {
        int maxCount = 0;
        Map.Entry<Character, Integer> maxEntrySet = null;
        for (Map.Entry<Character, Integer> entry : cardCounter.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxEntrySet = entry;
            }
        }
        return maxEntrySet;
    }
}
