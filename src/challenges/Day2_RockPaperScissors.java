package challenges;

import lib.InputFileReader;
import rps.RockPaperScissorsJudge;
import rps.RockPaperScissorsScore;
import rps.RockPaperScissorsTurn;

import java.io.IOException;
import java.util.Hashtable;

public class Day2_RockPaperScissors {
    /*
    one-star
    Appreciative of your help yesterday, one Elf gives you an encrypted strategy guide (your puzzle input)
    that they say will be sure to help you win. "The first column is what your opponent is going to play:
    A for Rock, B for Paper, and C for Scissors. The second column--" Suddenly, the Elf is called away to help with someone's tent.

    The second column, you reason, must be what you should play in response:
    X for Rock, Y for Paper, and Z for Scissors. Winning every time would be suspicious, so the responses must
    have been carefully chosen.

    The winner of the whole tournament is the player with the highest score.
    Your total score is the sum of your scores for each round.
    The score for a single round is the score for the shape you selected (1 for Rock, 2 for Paper, and 3 for Scissors)
    plus the score for the outcome of the round (0 if you lost, 3 if the round was a draw, and 6 if you won).
     */

    /*
    two-star
    "Anyway, the second column says how the round needs to end: X means you need to lose, Y means you need to end the round
     in a draw, and Z means you need to win. Good luck!"
     */

    public static void main(String[] args)  {
        Day2_RockPaperScissors day2RockPaperScissors = new Day2_RockPaperScissors();
        System.out.println("One Star: " + day2RockPaperScissors.getMyRPSScore());
        System.out.println("Two Stars: " + day2RockPaperScissors.getMyRPSScoreV2());
    }

    private final RockPaperScissorsJudge rpsJudge = new RockPaperScissorsJudge();
    private final Hashtable<String, RockPaperScissorsTurn> opponentTurnMap = new Hashtable<>();
    private final Hashtable<String, RockPaperScissorsTurn> myTurnMap = new Hashtable<>();
    private final Hashtable<String, RockPaperScissorsScore> expectedResultMap = new Hashtable<>();
    private String fileContents = "";
    public Day2_RockPaperScissors() {
        this.opponentTurnMap.put("A", RockPaperScissorsTurn.Rock);
        this.opponentTurnMap.put("B", RockPaperScissorsTurn.Paper);
        this.opponentTurnMap.put("C", RockPaperScissorsTurn.Scissors);

        this.myTurnMap.put("X", RockPaperScissorsTurn.Rock);
        this.myTurnMap.put("Y", RockPaperScissorsTurn.Paper);
        this.myTurnMap.put("Z", RockPaperScissorsTurn.Scissors);

        this.expectedResultMap.put("X", RockPaperScissorsScore.Lose);
        this.expectedResultMap.put("Y", RockPaperScissorsScore.Draw);
        this.expectedResultMap.put("Z", RockPaperScissorsScore.Win);

        String inputFile = "resources\\day2-input.txt";
        try {
            this.fileContents = InputFileReader.readFileAsString(inputFile);
        } catch (IOException e) {
            System.err.println("Exception: " + e);
        }
    }

    public int getMyRPSScore() {
        String[] lines = fileContents.split("\n");
        int score = 0;
        for (String line : lines) {
            if (line != null) {
                String[] turns = line.split(" ");
                if (turns.length == 2) {
                    RockPaperScissorsTurn opponentTurn = opponentTurnMap.get(turns[0]);
                    RockPaperScissorsTurn myTurn = myTurnMap.get(turns[1]);
                    score += rpsJudge.getMyScore(opponentTurn, myTurn);
                }
            }
        }
        return score;
    }

    public int getMyRPSScoreV2() {
        String[] lines = fileContents.split("\n");
        int score = 0;
        for (String line : lines) {
            if (line != null) {
                String[] turns = line.split(" ");
                if (turns.length == 2) {
                    RockPaperScissorsTurn opponentTurn = opponentTurnMap.get(turns[0]);
                    RockPaperScissorsScore expectedScore = expectedResultMap.get(turns[1]);
                    score += rpsJudge.getMyScoreV2(opponentTurn, expectedScore);
                }
            }
        }
        return score;
    }
}
