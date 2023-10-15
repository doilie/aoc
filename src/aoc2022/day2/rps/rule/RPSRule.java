package aoc2022.day2.rps.rule;

import aoc2022.day2.rps.RPSTurnType;

import java.util.Hashtable;

public abstract class RPSRule {
    private final Hashtable<RPSTurnType, RPSTurnType> winCondition = new Hashtable<>();
    private final Hashtable<RPSTurnType, RPSTurnType> loseCondition = new Hashtable<>();

    private final Hashtable<String, RPSTurnType> opponentTurnMap = new Hashtable<>();

    protected RPSRule() {
        winCondition.put(RPSTurnType.Scissors, RPSTurnType.Paper);
        winCondition.put(RPSTurnType.Paper, RPSTurnType.Rock);
        winCondition.put(RPSTurnType.Rock, RPSTurnType.Scissors);

        for (RPSTurnType turn : winCondition.keySet()) {
            loseCondition.put(winCondition.get(turn), turn);
        }

        this.opponentTurnMap.put("A", RPSTurnType.Rock);
        this.opponentTurnMap.put("B", RPSTurnType.Paper);
        this.opponentTurnMap.put("C", RPSTurnType.Scissors);
    }

    protected RPSTurnType getOpponentTurn(String input) {
        return this.opponentTurnMap.get(input);
    }

    protected RPSTurnType getWinTurn(RPSTurnType turn) {
        return this.winCondition.get(turn);
    }
    protected RPSTurnType getLoseTurn(RPSTurnType turn) {
        return this.loseCondition.get(turn);
    }

    public abstract int computeScore(String input1, String input2);
}
