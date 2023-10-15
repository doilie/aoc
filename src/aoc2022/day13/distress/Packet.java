package aoc2022.day13.distress;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Packet implements Comparable<Packet> {
    private final List<Object> list;

    public Packet(String packetString) {
        list = initializeList(packetString);
    }

    private List<Object> initializeList(String packetString) {
        Stack<List<Object>> listStack = new Stack<>();
        StringBuilder acc = new StringBuilder();
        for (int i = 0; i < packetString.length(); i++) {
            String currChar = Character.toString(packetString.charAt(i));
            switch (currChar) {
                case "[": {
                    // create new list and push to stack
                    listStack.push(new ArrayList<>());
                    // initialize acc
                    acc = new StringBuilder();
                    break;
                }
                case "]": {
                    // add acc and pop stack
                    if (!acc.toString().isEmpty()) {
                        listStack.peek().add(Integer.parseInt(acc.toString()));
                    }
                    List<Object> finishedList = listStack.pop();
                    // initialize acc
                    acc = new StringBuilder();

                    // peek stack and add popped list
                    if (!listStack.isEmpty()) {
                        listStack.peek().add(finishedList);
                    }
                    else {
                        // if last list, return value
                        return finishedList;
                    }
                    break;
                }
                case ",": {
                    // add acc and prep for new element
                    if (!acc.toString().isEmpty()) {
                        listStack.peek().add(Integer.parseInt(acc.toString()));
                        acc = new StringBuilder();
                    }
                    break;
                }
                default: {
                    acc.append(currChar);
                    break;
                }
            }
        }
        return null;
    }


    @Override
    public int compareTo(Packet packet) {
        return compare(new ArrayList<>(this.getList()), new ArrayList<>(packet.getList()));
    }

    public List<Object> getList() {
        return list;
    }

    /**
     * When comparing two values, the first value is called left and the second value is called right. Then:
     * <p>
     *   If both values are integers, the lower integer should come first.
     *   If the left integer is lower than the right integer, the inputs are in the right order.
     *   If the left integer is higher than the right integer, the inputs are not in the right order.
     *   Otherwise, the inputs are the same integer; continue checking the next part of the input.
     * <p>
     *   If both values are lists, compare the first value of each list, then the second value, and so on.
     *   If the left list runs out of items first, the inputs are in the right order.
     *   If the right list runs out of items first, the inputs are not in the right order.
     *   If the lists are the same length and no comparison makes a decision about the order,
     *   continue checking the next part of the input.
     * <p>
     *   If exactly one value is an integer, convert the integer to a list which contains that integer as its only value,
     *   then retry the comparison. For example, if comparing [0,0,0] and 2, convert the right value to [2] (a list containing 2);
     *   the result is then found by instead comparing [0,0,0] and [2].
     *
     */
    private static int compare(Object e1, Object e2) {
        int res = 0;
        if (e1.getClass().equals(Integer.class) && e2.getClass().equals(Integer.class)) {
            return ((Integer) e1).compareTo((Integer) e2);
        }

        if (e1.getClass().equals(ArrayList.class) || e2.getClass().equals(ArrayList.class)) {
            if (e1.getClass().equals(ArrayList.class) && e2.getClass().equals(Integer.class)) {
                e2 = new ArrayList<>(List.of(e2));
            }
            else if (e2.getClass().equals(ArrayList.class) && e1.getClass().equals(Integer.class)) {
                e1 = new ArrayList<>(List.of(e1));
            }

            ArrayList<Object> list1 = new ArrayList<>((ArrayList<Object>) e1);
            ArrayList<Object> list2 = new ArrayList<>((ArrayList<Object>) e2);

            while (true) {
                if (list1.isEmpty() && list2.isEmpty()) {
                    return 0;
                }
                else if (list1.isEmpty()) {
                    return -1;
                }
                else if (list2.isEmpty()) {
                    return 1;
                }
                int compareRes = compare(list1.remove(0), list2.remove(0));
                if (compareRes != 0) {
                    return compareRes;
                }
            }
        }
        return res;
    }
}
