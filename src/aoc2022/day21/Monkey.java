package aoc2022.day21;

import java.util.Hashtable;
import java.util.Stack;

public abstract class Monkey {
    private final String name;
    private String parent;

    protected Monkey(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract long doTask();

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public static Monkey buildMonkey(String line, Hashtable<String, Monkey> monkeys) {
        String[] lineParts = line.split("\\s");
        if (lineParts.length == 2) {
            return new YellerMonkey(lineParts[0].substring(0, lineParts[0].length() -1), Long.parseLong(lineParts[1]));
        }
        else if (lineParts.length == 4) {
            return new OperationMonkey(lineParts[0].substring(0, lineParts[0].length() -1), lineParts[1], lineParts[3], OperationMonkey.getOperation(lineParts[2]), monkeys);
        }
        return null;
    }

    public static long getReplacementValueForMonkey(String sourceMonkey, Hashtable<String, Monkey> monkeys) {
        Monkey sourceMonkeyObj = monkeys.get(sourceMonkey);
        Stack<Monkey> monkeyParents = getMonkeysToRoot(sourceMonkeyObj, monkeys);
        long targetValue = -1;
        while (!monkeyParents.isEmpty()) {
            Monkey monkeyToAdjust = monkeyParents.pop();
            OperationMonkey parentMonkey = (OperationMonkey) monkeys.get(monkeyToAdjust.getParent());
            Monkey monk1 = monkeys.get(parentMonkey.getMonkey1());
            Monkey monk2 = monkeys.get(parentMonkey.getMonkey2());
            long monk1Result = monk1.doTask();
            long monk2Result = monk2.doTask();
            OperationMonkey.Operation operation = parentMonkey.getOperation();
//            System.out.println("parentMonkey: " + parentMonkey.getName() +
//                    ", monkeyToAdjust: " + monkeyToAdjust.getName() +
//                    ", monkey 1: " + monk1.getName() +
//                    ", monkey 1 result: " + monk1Result +
//                    ", monkey 2: " + monk2.getName() +
//                    ", monkey 2 result: " + monk2Result +
//                    ", operation: " + operation);
            long fixedValue;
            boolean isLhs;
            if (monkeyToAdjust.equals(monk1)) {
                fixedValue = monk2Result;
                isLhs = true;
            }
            else {
                fixedValue = monk1Result;
                isLhs = false;
            }
            if (targetValue == -1) {
                targetValue = fixedValue;
            }
            else {
                targetValue = OperationMonkey.performOppositeOperation(targetValue, fixedValue, operation, isLhs);
            }
//            System.out.println("target value for " + monkeyToAdjust.getName() + " : " + targetValue);
        }
        return targetValue;
    }

    private static Stack<Monkey> getMonkeysToRoot(Monkey sourceMonkey, Hashtable<String, Monkey> monkeys) {
        Stack<Monkey> parents = new Stack<>();
        Monkey parent = sourceMonkey;
        while (parent.getParent() != null) {
            parents.push(parent);
            if (parent.getParent() != null) {
                parent = monkeys.get(parent.getParent());
            }
        }
        return parents;
    }

}
