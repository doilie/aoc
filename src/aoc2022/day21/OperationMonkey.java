package aoc2022.day21;

import java.util.Hashtable;

public class OperationMonkey extends Monkey {
    public enum Operation {
        Add,
        Subtract,
        Multiply,
        Divide
    }

    private final String monkey1;
    private final String monkey2;
    private final Operation operation;
    private final Hashtable<String, Monkey> monkeys;
    private long result;

    public OperationMonkey(String name, String monkey1, String monkey2, Operation operation, Hashtable<String, Monkey> monkeys) {
        super(name);
        this.monkey1 = monkey1;
        this.monkey2 = monkey2;
        this.operation = operation;
        this.monkeys = monkeys;
    }

    public String getMonkey1() {
        return monkey1;
    }

    public String getMonkey2() {
        return monkey2;
    }

    public Operation getOperation() {
        return operation;
    }

    @Override
    public long doTask() {
        Monkey monkey1Obj = monkeys.get(monkey1);
        Monkey monkey2Obj = monkeys.get(monkey2);
        monkey1Obj.setParent(this.getName());
        monkey2Obj.setParent(this.getName());

        switch (operation) {
            case Add: result = monkey1Obj.doTask() + monkey2Obj.doTask(); break;
            case Subtract: result = monkey1Obj.doTask() - monkey2Obj.doTask(); break;
            case Multiply: result = monkey1Obj.doTask() * monkey2Obj.doTask(); break;
            case Divide: result = monkey1Obj.doTask() / monkey2Obj.doTask(); break;
        }

        return this.result;
    }

    public static Operation getOperation(String symbol) {
        switch(symbol) {
            case "+": return Operation.Add;
            case "-": return Operation.Subtract;
            case "/": return Operation.Divide;
            case "*": return Operation.Multiply;
        }
        return null;
    }

    public static long performOppositeOperation(long result, long fixedValue, Operation operation, boolean isLhs) {
        switch(operation) {
            case Add: return performOperation(result, fixedValue, Operation.Subtract);
            case Subtract: return isLhs ?
                    performOperation(fixedValue, result, Operation.Add) :
                    performOperation(fixedValue, result, Operation.Subtract);
            case Multiply: return performOperation(result, fixedValue, Operation.Divide);
            case Divide: return isLhs ?
                    performOperation(fixedValue, result, Operation.Multiply) :
                    performOperation(fixedValue, result, Operation.Divide);
        }

        return 0;
    }

    public static long performOperation(long value1, long value2, Operation operation) {
        switch (operation) {
            case Add:
                return value1 + value2;
            case Subtract:
                return value1 - value2;
            case Multiply:
                return value1 * value2;
            case Divide:
                return value1 / value2;
        }
        return 0;
    }
}
