package aoc2020.day7;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BagRules
{
    private static final String BAGS_CONTAIN = " bags contain ";
    private static final String BAG = "bag";
    private final Map<String, Map<String, Integer>> bagRuleMap = new HashMap<>();

    void addBagRule(String rule)
    {
        // get key
        int keyBagIndex = rule.indexOf(BAGS_CONTAIN);
        String key = rule.substring(0, keyBagIndex).trim();

        // get values
        Map<String, Integer> bagContents = new HashMap<>();
        int nextBagIndex = keyBagIndex + BAGS_CONTAIN.length();
        while (nextBagIndex != 0)
        {
            int bagWordIndex = rule.indexOf(BAG, nextBagIndex);
            int bagDescIdx = rule.indexOf(" ", nextBagIndex);
            int numBags;
            try
            {
                numBags = Integer.parseInt(rule.substring(nextBagIndex, bagDescIdx).trim());
                String bagDesc = rule.substring(bagDescIdx, bagWordIndex).trim();
                bagContents.put(bagDesc, numBags);
            }
            catch(NumberFormatException ignored) {}

            nextBagIndex = rule.indexOf(" ", bagWordIndex) + 1;
        }

        bagRuleMap.put(key, bagContents);
    }

    Map<String, Integer> getBagContents(String bagType)
    {
        return bagRuleMap.get(bagType);
    }

    void getContainingBags(String bagType, Set<String> containingBags)
    {
        for (String bagRule : bagRuleMap.keySet())
        {
            if (!bagRule.equals(bagType) && !containingBags.contains(bagRule))
            {
                Map<String, Integer> bagContents = bagRuleMap.get(bagRule);
                if (bagContents.containsKey(bagType))
                {
                    containingBags.add(bagRule);
                    getContainingBags(bagRule, containingBags);
                }
            }
        }
    }

    void getContainedBags(String bagType, int factor, Map<String, Integer> containedBags)
    {
        Map<String, Integer> bagContents = bagRuleMap.get(bagType);
        for (String bagContentsTypeKey : bagContents.keySet())
        {
            int bagContentsTypeQty = bagContents.get(bagContentsTypeKey);
            if (!containedBags.containsKey(bagContentsTypeKey))
            {
                containedBags.put(bagContentsTypeKey, 0);
            }
            containedBags.put(bagContentsTypeKey, containedBags.get(bagContentsTypeKey) + factor * bagContentsTypeQty);
            getContainedBags(bagContentsTypeKey, factor * bagContentsTypeQty, containedBags);
        }
    }
}
