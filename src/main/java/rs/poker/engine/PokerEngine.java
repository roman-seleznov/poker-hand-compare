package rs.poker.engine;

import rs.poker.PokerHand;
import rs.poker.PokerHandValue;

public interface PokerEngine {

    /**
     * Calculates value of a Poker Hand.
     * @param hand - a Poker hand to calculate value for.
     * @return - Calculated poker hand value.
     */
    PokerHandValue getHandValue(PokerHand hand);

}
