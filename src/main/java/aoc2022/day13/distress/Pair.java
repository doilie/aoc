package aoc2022.day13.distress;


public class Pair {
    private final Packet element1;
    private final Packet element2;

    public Pair(String element1Str, String element2Str) {
        this.element1 = new Packet(element1Str);
        this.element2 = new Packet(element2Str);
    }

    public Packet getElement1() {
        return element1;
    }

    public Packet getElement2() {
        return element2;
    }

    public boolean compare() {
        return element1.compareTo(element2) < 0;
    }
}
