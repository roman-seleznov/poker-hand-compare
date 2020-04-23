package com.clarusone.poker.engine;

import com.clarusone.poker.Card;
import com.clarusone.poker.PokerHand;
import com.clarusone.poker.PokerHandValue;
import com.clarusone.poker.rules.PokerHandValuationRule;
import com.clarusone.poker.rules.texasholdem.HighValueOrStraightValuationRule;
import com.clarusone.poker.rules.texasholdem.ComboValidationRule;

import java.util.HashSet;
import java.util.Set;

public class TexasHoldemEngine implements PokerEngine {

    TexasHoldemEngine() {
    }

    /**
     * Since these rules contain state - create an instance every time
     * @return A set of rules to process
     */
    private Set<PokerHandValuationRule> getValuationRules() {
        Set<PokerHandValuationRule> rules = new HashSet<>();

        rules.add(new HighValueOrStraightValuationRule());
        rules.add(new ComboValidationRule());

        return rules;
    }

    @Override
    public PokerHandValue getHandValue(PokerHand hand) {

        Set<Card> setOfCards = hand.getSetOfCards();
        PokerHandValue highestRank = new PokerHandValue();
        highestRank.setRank(-1000);

        for (PokerHandValuationRule rule : getValuationRules()) {
            PokerHandValue value = rule.checkRuleValue(setOfCards);
            if (value != null && value.getRank() > highestRank.getRank()) {
                highestRank = value;
            }
        }


        return highestRank;
    }
}
