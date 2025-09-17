package aoc2022.day13;

import aoc2022.day13.distress.Packet;
import aoc2022.day13.distress.Pair;
import lib.Challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Day13_DistressSignal extends Challenge {
    public static void main(String[] args) {
        Day13_DistressSignal day13 = new Day13_DistressSignal();
        day13.doOneStarSolution();
        day13.doTwoStarSolution();
    }

    public Day13_DistressSignal() {
        super("2022/day13-input.txt");
        parseFile();
    }

    private List<Pair> pairList = new ArrayList<>();

    @Override
    protected void parseFile() {
        String[] lines = this.getFileContents().split("\n");
        pairList = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            if (!lines[i].isEmpty()) {
                pairList.add(new Pair(lines[i], lines[++i]));
            }
        }
    }

    @Override
    public void doOneStarSolution() {
        parseFile();

        int idx = 1;
        int sumValidIdx = 0;
        for (Pair pair : pairList) {
            boolean result = pair.compare();
            if (result) {
                sumValidIdx += idx;
            }
            idx++;
        }

        System.out.println("Sum of indices with right order of pairs: " + sumValidIdx);
    }

    @Override
    public void doTwoStarSolution() {
        parseFile();

        Pair dividerPackets = new Pair("[[2]]", "[[6]]");
        pairList.add(dividerPackets);

        TreeSet<Packet> packetTree = new TreeSet<>();
        for (Pair pair : pairList) {
            packetTree.add(pair.getElement1());
            packetTree.add(pair.getElement2());
        }

        int idx = 1;
        int product = 1;
        for (Packet p : packetTree) {
            if (p == dividerPackets.getElement1()) {
                product *= idx;
            }
            else if (p == dividerPackets.getElement2()) {
                product *= idx;
            }
            idx++;
        }
        System.out.println("Product of indices of divider packets: " + product);
    }
}
