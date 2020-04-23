package com.clarusone.poker.rules;

import com.clarusone.poker.Card;
import com.clarusone.poker.PokerHandValue;

import java.util.Set;

public interface PokerHandValuationRule {
    PokerHandValue checkRuleValue(Set<Card> setOfCards);
}
