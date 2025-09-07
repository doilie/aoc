package aoc2023.day7;

public class CamelCardHand implements Comparable<CamelCardHand> {
    private static final String CARD_ORDER = "AKQJT98765432";
    private static final String CARD_ORDER_JOKER = "AKQT98765432J";
    private static String cardOrder = CARD_ORDER;

    public static void useJokerMode() {
        cardOrder = CARD_ORDER_JOKER;
    }

    public static void useNormalMode() {
        cardOrder = CARD_ORDER;
    }

    private String cards;
    private int bid;
    private CamelCardType type;

    public CamelCardHand(String line) {
        String[] lineParts = line.split("\\s+");
        if (lineParts.length == 2) {
            cards = lineParts[0];
            bid = Integer.parseInt(lineParts[1]);
            type = cardOrder.equals(CARD_ORDER) ? CamelCardType.getType(cards) : CamelCardType.getTypeAsJoker(cards);
        }
    }

    public int getBid() {
        return bid;
    }

    @Override
    public int compareTo(CamelCardHand o) {
        int compValue = this.type.getValue() - o.type.getValue();
        if (compValue == 0) {
            for (int i = 0; i < this.cards.length(); i++) {
                String thisCard = Character.toString(this.cards.charAt(i));
                String otherCard = Character.toString(o.cards.charAt(i));
                int cardCompValue = cardOrder.indexOf(otherCard) - cardOrder.indexOf(thisCard);
                if (cardCompValue != 0) {
                    return cardCompValue;
                }
            }
        }
        return compValue;
    }

    @Override
    public String toString() {
        return cards + " " + bid + " " + type;
    }
}
