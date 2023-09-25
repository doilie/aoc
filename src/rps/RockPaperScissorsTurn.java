package rps;

public enum RockPaperScissorsTurn {
    Rock(1),
    Paper(2),
    Scissors(3);

    private final int score;
    RockPaperScissorsTurn(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

}
