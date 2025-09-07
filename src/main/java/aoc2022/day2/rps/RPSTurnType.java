package aoc2022.day2.rps;

public enum RPSTurnType {
    Rock(1),
    Paper(2),
    Scissors(3);

    private final int score;
    RPSTurnType(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

}
