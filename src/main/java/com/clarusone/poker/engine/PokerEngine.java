package com.clarusone.poker.engine;

import com.clarusone.poker.PokerHand;
import com.clarusone.poker.PokerHandValue;

public interface PokerEngine {

    /**
     * Calculates value of a Poker Hand.
     * @param hand - a Poker hand to calculate value for.
     * @return - Calculated poker hand value.
     */
    PokerHandValue getHandValue(PokerHand hand);

}
