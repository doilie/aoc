package aoc2022.day15;

public class Range implements Comparable<Range> {
    private final int start;
    private final int end;

    public Range(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public int compareTo(Range o) {
        if (this.start == o.start && this.end == o.end) {
            return 0;
        }
        else if (this.start != o.start) {
            return this.start - o.start;
        }
        else {
            return this.end - o.end;
        }
    }

    @Override
    public String toString() {
        return start + "," + end;
    }

    public Range combineRange(Range toCombine) {
        if ((this.getEnd() >= toCombine.getStart() && this.getStart() <= toCombine.getEnd()) || (this.getEnd() == toCombine.getStart() - 1)) {
            return new Range(Math.min(this.getStart(), toCombine.getEnd()), Math.max(this.getEnd(), toCombine.getEnd()));
        }
        return null;
    }
}
