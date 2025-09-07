package aoc2018.day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReactionRoundTest {

    @Test
    void react_aA()
    {
        assertEquals("", ReactionRound.react("aA"));
    }

    @Test
    void react_abBA()
    {
        assertEquals("", ReactionRound.react(ReactionRound.react("abBA")));
    }

    @Test
    void react_abAB()
    {
        assertEquals("abAB", ReactionRound.react("abAB"));
    }

    @Test
    void react_aabAAB()
    {
        assertEquals("aabAAB", ReactionRound.react("aabAAB"));
    }

    @Test
    void reactContinuously_abBA()
    {
        assertEquals("", ReactionRound.reactContinuously("abBA"));
    }

    @Test
    void reactContinuously_dabAcCaCBAcCcaDA()
    {
        assertEquals("dabCBAcaDA", ReactionRound.reactContinuously("dabAcCaCBAcCcaDA"));
    }

    @Test
    void reactContinuouslyWithoutUnit_dabAcCaCBAcCcaDA_a()
    {
        assertEquals("dbCBcD", ReactionRound.reactContinuouslyWithoutUnit("dabAcCaCBAcCcaDA", "a"));
    }

    @Test
    void reactContinuouslyWithoutUnit_dabAcCaCBAcCcaDA_b()
    {
        assertEquals("daCAcaDA", ReactionRound.reactContinuouslyWithoutUnit("dabAcCaCBAcCcaDA", "b"));
    }

    @Test
    void reactContinuouslyWithoutUnit_dabAcCaCBAcCcaDA_c()
    {
        assertEquals("daDA", ReactionRound.reactContinuouslyWithoutUnit("dabAcCaCBAcCcaDA", "c"));
    }

    @Test
    void reactContinuouslyWithoutUnit_dabAcCaCBAcCcaDA_d()
    {
        assertEquals("abCBAc", ReactionRound.reactContinuouslyWithoutUnit("dabAcCaCBAcCcaDA", "d"));
    }

    @Test
    void getShortestPolymerFromRemovingUnit_dabAcCaCBAcCcaDA()
    {
        assertEquals("daDA", ReactionRound.getShortestPolymerFromRemovingUnit("dabAcCaCBAcCcaDA"));
    }
}