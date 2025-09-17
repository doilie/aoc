package aoc2022.day22;

public enum FacingDirection {
    Right(">"),
    Left("<"),
    Up("^"),
    Down("v");

    public final String symbol;

    FacingDirection(String symbol) {
        this.symbol = symbol;
    }
}
