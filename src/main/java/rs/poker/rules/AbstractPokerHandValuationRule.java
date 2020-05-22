package rs.poker.rules;

import rs.poker.Card;
import rs.poker.CardSuit;
import rs.poker.CardValue;
import rs.poker.PokerHandValue;

import java.util.*;

public abstract class AbstractPokerHandValuationRule implements PokerHandValuationRule {

    protected Map<CardValue, List<Card>> valueMap = new HashMap<>();
    protected Map<CardSuit, Card> cardSuitCardMap = new HashMap<>();
    protected SortedSet<Card> sortedCards = new TreeSet<>();

    @Override
    public PokerHandValue checkRuleValue(Set<Card> setOfCards) {

        for (Card card : setOfCards) {

            List<Card> valueList = valueMap.computeIfAbsent(card.getCardValue(), k -> new LinkedList<>());
            valueList.add(card);

            cardSuitCardMap.put(card.getCardSuit(), card);
            sortedCards.add(card);
        }

        return calculateRuleValue();

    }

    protected abstract PokerHandValue calculateRuleValue();

}
