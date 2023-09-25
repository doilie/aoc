package rps;

import java.util.Hashtable;

public class RockPaperScissorsJudge {
    private final Hashtable<RockPaperScissorsTurn, RockPaperScissorsTurn> winCondition = new Hashtable<>();
    private final Hashtable<RockPaperScissorsTurn, RockPaperScissorsTurn> loseCondition = new Hashtable<>();


    public RockPaperScissorsJudge() {
        winCondition.put(RockPaperScissorsTurn.Scissors, RockPaperScissorsTurn.Paper);
        winCondition.put(RockPaperScissorsTurn.Paper, RockPaperScissorsTurn.Rock);
        winCondition.put(RockPaperScissorsTurn.Rock, RockPaperScissorsTurn.Scissors);
        for (RockPaperScissorsTurn turn : winCondition.keySet()) {
            loseCondition.put(winCondition.get(turn), turn);
        }
    }

    public int getMyScore(RockPaperScissorsTurn opponentTurn, RockPaperScissorsTurn myTurn) {
        return myTurn.getScore() + this.checkWin(opponentTurn, myTurn).getScore();
    }

    public int getMyScoreV2(RockPaperScissorsTurn opponentTurn, RockPaperScissorsScore score) {
        return score.getScore() + this.getMyTurn(opponentTurn, score).getScore();
    }

    private RockPaperScissorsTurn getMyTurn(RockPaperScissorsTurn opponentTurn, RockPaperScissorsScore score) {
        if (score.equals(RockPaperScissorsScore.Win)) {
            return loseCondition.get(opponentTurn);
        }
        else if (score.equals(RockPaperScissorsScore.Lose)) {
            return winCondition.get(opponentTurn);
        }
        return opponentTurn;
    }

    private RockPaperScissorsScore checkWin(RockPaperScissorsTurn opponentTurn, RockPaperScissorsTurn myTurn) {
        if (opponentTurn.equals(myTurn)) {
            return RockPaperScissorsScore.Draw;
        }
        else if (winCondition.get(myTurn).equals(opponentTurn)) {
            return RockPaperScissorsScore.Win;
        }
        return RockPaperScissorsScore.Lose;
    }
}
