package aoc2022.day2.rps.rule;

import aoc2022.day2.rps.RPSResult;
import aoc2022.day2.rps.RPSTurnType;

import java.util.Hashtable;

public class RPSMyTurnRule extends RPSRule {
    private final Hashtable<String, RPSTurnType> myTurnMap = new Hashtable<>();

    public RPSMyTurnRule() {
        this.myTurnMap.put("X", RPSTurnType.Rock);
        this.myTurnMap.put("Y", RPSTurnType.Paper);
        this.myTurnMap.put("Z", RPSTurnType.Scissors);
    }

    @Override
    public int computeScore(String input1, String input2) {
        RPSTurnType opponentTurn = this.getOpponentTurn(input1);
        RPSTurnType myTurn = this.myTurnMap.get(input2);
        int resultScore = 0;
        if (opponentTurn.equals(myTurn)) {
            resultScore += RPSResult.Draw.getScore();
        }
        else if (this.getWinTurn(myTurn).equals(opponentTurn)) {
            resultScore += RPSResult.Win.getScore();
        }
        return myTurn.getScore() + resultScore;
    }
}
