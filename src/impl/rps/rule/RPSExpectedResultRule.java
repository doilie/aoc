package impl.rps.rule;

import impl.rps.RPSResult;
import impl.rps.RPSTurnType;

import java.util.Hashtable;

public class RPSExpectedResultRule extends RPSRule {
    private final Hashtable<String, RPSResult> expectedResultMap = new Hashtable<>();

    public RPSExpectedResultRule() {
        super();
        this.expectedResultMap.put("X", RPSResult.Lose);
        this.expectedResultMap.put("Y", RPSResult.Draw);
        this.expectedResultMap.put("Z", RPSResult.Win);
    }

    @Override
    public int computeScore(String input1, String input2) {
        RPSTurnType opponentTurn = this.getOpponentTurn(input1);
        RPSResult expectedResult = this.expectedResultMap.get(input2);
        RPSTurnType myTurn = opponentTurn;
        if (expectedResult.equals(RPSResult.Win)) {
            myTurn = this.getLoseTurn(opponentTurn);
        }
        else if (expectedResult.equals(RPSResult.Lose)) {
            myTurn = this.getWinTurn(opponentTurn);
        }

        return expectedResult.getScore() + myTurn.getScore();
    }
}
