package rs.poker.rules;

import rs.poker.Card;
import rs.poker.PokerHandValue;

import java.util.Set;

public interface PokerHandValuationRule {
    PokerHandValue checkRuleValue(Set<Card> setOfCards);
}
