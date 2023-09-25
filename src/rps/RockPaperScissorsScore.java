package rps;

public enum RockPaperScissorsScore {
    Win(6),
    Draw(3),
    Lose(0);

    private final int score;

    RockPaperScissorsScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
