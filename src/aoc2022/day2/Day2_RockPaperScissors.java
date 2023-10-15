package aoc2022.day2;

import aoc2022.day2.rps.RPSGame;
import aoc2022.day2.rps.rule.RPSExpectedResultRule;
import aoc2022.day2.rps.rule.RPSMyTurnRule;
import lib.Challenge;

public class Day2_RockPaperScissors extends Challenge {
    public static void main(String[] args)  {
        Day2_RockPaperScissors day2 = new Day2_RockPaperScissors();
        day2.doOneStarSolution();
        day2.doTwoStarSolution();
    }

    private final RPSGame game;

    public Day2_RockPaperScissors() {
        super("2022/day2-input.txt");
        this.game = new RPSGame(new RPSMyTurnRule());
    }

    @Override
    protected void parseFile() {
        String[] lines = this.getFileContents().split("\n");
        if (game != null) {
            for (String line : lines) {
                if (line != null) {
                    String[] turns = line.split(" ");
                    game.doGame(turns[0], turns[1]);
                }
            }
        }
    }

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
    @Override
    public void doOneStarSolution() {
        this.parseFile();
        System.out.println("Input based on my turn: " + this.game.getMyTotalScore());
    }

    /*
     two-star
    "Anyway, the second column says how the round needs to end: X means you need to lose, Y means you need to end the round
     in a draw, and Z means you need to win. Good luck!"
     */
    @Override
    public void doTwoStarSolution() {
        game.changeRule(new RPSExpectedResultRule());
        this.parseFile();
        System.out.println("Input based on expected result: " + this.game.getMyTotalScore());
    }
}
