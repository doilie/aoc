package impl.rps;

public enum RPSResult {
    Win(6),
    Draw(3),
    Lose(0);

    private final int score;

    RPSResult(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
