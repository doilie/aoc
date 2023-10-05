package impl.monkey;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Monkey {
    private final List<Long> items = new ArrayList<>();
    private Function<Long, Long> operationFunction = i -> i;
    private int inspectionCount = 0;
    private boolean beRelieved = true;
    private long absoluteLimit;
    private long divisibleByValue;
    private int monkeyTrue;
    private int monkeyFalse;

    public int inspect() {
        int monkeyFriendNumber = -1;
        if (!this.items.isEmpty()) {
            // count
            inspectionCount++;

            // operation
            long item = getCurrentItem();
            item = item % absoluteLimit;  // keep number from being too large
            item = operationFunction.apply(item);
            if (beRelieved) {
                item = item / 3;
            }
            items.set(0, item);

            // test
            if (item % divisibleByValue == 0) {
                return monkeyTrue;
            }
            else {
                return monkeyFalse;
            }
        }
        return monkeyFriendNumber;
    }

    public Long getCurrentItem() {
        if (!this.items.isEmpty()) {
            return this.items.get(0);
        }
        return null;
    }
    public void throwItem(Monkey monkeyFriend) {
        if (!this.items.isEmpty()) {
            monkeyFriend.catchItem(this.items.remove(0));
        }
    }

    public void catchItem(long item) {
        items.add(item);
    }

    public void addItems(List<Long> items) {
        this.items.addAll(items);
    }

    public void setOperationFunction(Function<Long, Long> operationFunction) {
        this.operationFunction = operationFunction;
    }

    public long getInspectionCount() {
        return inspectionCount;
    }

    public void setBeRelieved(boolean beRelieved) {
        this.beRelieved = beRelieved;
    }

    public void setAbsoluteLimit(Long absoluteLimit) {
        this.absoluteLimit = absoluteLimit;
    }

    public long getDivisibleByValue() {
        return divisibleByValue;
    }

    public void setDivisibleByValue(Long divisibleByValue) {
        this.divisibleByValue = divisibleByValue;
    }

    public void setMonkeyTrue(int monkeyTrue) {
        this.monkeyTrue = monkeyTrue;
    }

    public void setMonkeyFalse(int monkeyFalse) {
        this.monkeyFalse = monkeyFalse;
    }
}
