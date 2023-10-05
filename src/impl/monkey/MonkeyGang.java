package impl.monkey;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MonkeyGang {
    private final List<Monkey> monkeys = new ArrayList<>();
    private long maxWorryValue = 1L;

    public void addMonkey(List<String> lines) {
        MonkeyParser parser = new MonkeyParser(lines);
        Monkey newMonkey = parser.parse();
        if (newMonkey != null) {
            monkeys.add(newMonkey);
            maxWorryValue *= newMonkey.getDivisibleByValue();
        }
    }

    public void setGangMembersMaxWorryValue() {
        monkeys.forEach(monkey -> monkey.setAbsoluteLimit(maxWorryValue));
    }

    public void removeRelief() {
        monkeys.forEach(monkey -> monkey.setBeRelieved(false));
    }

    public void inspectEachMonkey(int rounds) {
        for (int i = 0; i < rounds; i++) {
            for (Monkey monkey : monkeys) {
                while (monkey.getCurrentItem() != null) {
                    int monkeyToThrow = monkey.inspect();
                    monkey.throwItem(monkeys.get(monkeyToThrow));
                }
            }
        }
    }

    public long getProductOfMax2InspectionCounts() {
        List<Long> inspectionCounts = monkeys.stream().map(Monkey::getInspectionCount).sorted().collect(Collectors.toList());
        return inspectionCounts.get(inspectionCounts.size() - 2) * inspectionCounts.get(inspectionCounts.size() - 1);
    }
}
