package aoc2022.day21;

public class YellerMonkey extends Monkey {
    private final long number;

    public YellerMonkey(String name, long number) {
        super(name);
        this.number = number;
    }

    @Override
    public long doTask() {
        return number;
    }
}
