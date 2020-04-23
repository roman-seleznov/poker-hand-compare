package com.clarusone.poker.rules.texasholdem;

import com.clarusone.poker.Card;
import com.clarusone.poker.CardValue;
import com.clarusone.poker.PokerHandValue;
import com.clarusone.poker.rules.AbstractPokerHandValuationRule;

import java.util.Collections;
import java.util.SortedSet;

public class HighValueOrStraightValuationRule extends AbstractPokerHandValuationRule {

    @Override
    protected PokerHandValue calculateRuleValue() {

        //In case if it is straight or high value - 5 unique cards available
        if(valueMap.keySet().size() == 5) {

            boolean straight = checkIfStraight(sortedCards);
            boolean flush = cardSuitCardMap.keySet().size() == 1;

            PokerHandValue value = new PokerHandValue();

            if ( flush ) {

                if ( straight ) {
                    if(sortedCards.last().getCardValue() == CardValue.ACE) {
                        value.setRank(PokerHandRank.ROYALFLUSH.rank);
                    } else {
                        value.setRank(PokerHandRank.STRAIGHTFLUSH.rank);
                    }
                } else {
                    value.setRank(PokerHandRank.FLUSH.rank);
                }

            } else {

                if ( straight ) {
                    value.setRank(PokerHandRank.STRAIGHT.rank);
                } else {
                    value.setRank(PokerHandRank.HIGHCARD.rank);
                }

            }

            sortedCards.forEach( card -> value.getSortedValues().add(card.getCardValue()));
            Collections.reverse(value.getSortedValues());
            return value;

        }

        return null;
    }

    private boolean checkIfStraight(SortedSet<Card> cards) {
        Card previousCard = null;
        for (Card card : cards) {
            if (previousCard != null) {
                if (card.getCardValue().cardValue - previousCard.getCardValue().cardValue != 1) {
                    return false;
                }
            }
            previousCard = card;
        }
        return true;
    }


}
