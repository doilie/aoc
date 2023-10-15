package aoc2022.day3.rucksack;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;

public class Rucksack {
    private static final String PossibleItems = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String rucksackItems;

    public static int getItemPriority(String item) {
        if (item.length() != 1) {
            return 0;
        }
        return Rucksack.PossibleItems.indexOf(item) + 1;
    }

    public Rucksack(String rucksackItems) {
        this.rucksackItems = rucksackItems;
    }

    public String getMisplacedItem() {
        int compartmentSize = this.rucksackItems.length() / 2;
        String secondCompartment = this.rucksackItems.substring(compartmentSize);

        String commonItem = "";
        for (int i = 0; i < compartmentSize; i++) {
            String firstCompartmentItem = Character.toString(this.rucksackItems.charAt(i));
            if (secondCompartment.contains(firstCompartmentItem)) {
                commonItem = firstCompartmentItem;
                break;
            }
        }

        return commonItem;
    }

    public String getBadgeItem(List<Rucksack> otherRucksacks) {
        String commonItem = "";
        Hashtable<String, Integer> items = new Hashtable<>();

        for (int i = 0; i < this.rucksackItems.length(); i++) {
            String currItem = Character.toString(this.rucksackItems.charAt(i));
            items.putIfAbsent(currItem, 1);
        }

        int currCount = 2;
        Set<String> itemKeys = items.keySet();
        for (Rucksack otherRucksack : otherRucksacks) {
            for (String itemKey : itemKeys) {
                if (otherRucksack.rucksackItems.contains(itemKey) && items.get(itemKey) < currCount) {
                    int itemCount = items.get(itemKey);
                    items.put(itemKey, ++itemCount);
                }
            }
            currCount++;
        }

        for (String itemKey : itemKeys) {
            if (items.get(itemKey) == currCount - 1) {
                commonItem = itemKey;
                break;
            }
        }

        return commonItem;
    }

}
