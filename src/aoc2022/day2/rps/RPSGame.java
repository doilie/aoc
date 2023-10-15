package aoc2022.day2.rps;

import aoc2022.day2.rps.rule.RPSRule;

public class RPSGame {
    private RPSRule rule;
    private int myTotalScore = 0;

    public RPSGame(RPSRule rule) {
        this.rule = rule;
    }

    public void changeRule(RPSRule newRule) {
        this.myTotalScore = 0;
        this.rule = newRule;
    }

    public void doGame(String input1, String input2) {
        myTotalScore += this.rule.computeScore(input1, input2);
    }

    public int getMyTotalScore() {
        return myTotalScore;
    }
}
