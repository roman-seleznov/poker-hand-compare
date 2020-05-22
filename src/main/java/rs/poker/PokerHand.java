package rs.poker;

import rs.poker.engine.PokerEngine;
import rs.poker.engine.PokerEngineFactory;
import rs.poker.exception.InvalidCardException;
import rs.poker.exception.InvalidPokerHandException;

import java.util.HashSet;
import java.util.Set;

/**
 * An implementation of the PokerHand required for the coding test.
 *
 * The PokerHand class has a constructor that accepts a string containing 5 cards, e.g.
 *
 * PokerHand hand = new PokerHand("KS 2H 5C JD TD");
 * The characteristics of the string of cards are:
 *
 * Each card consists of two characters, where
 * The first character is the value of the card: 2, 3, 4, 5, 6, 7, 8, 9, T(en), J(ack), Q(ueen), K(ing), A(ce)
 * The second character represents the suit: S(pades), H(earts), D(iamonds), C(lubs)
 * A space is used as a separator between cards
 * In this poker game, Aces are always the highest card in the deck (14), so "AS KH QC JD TD" is a straight, but "AS 2H 3C 4D 5D" is not.
 *
 */
public class PokerHand implements Comparable<PokerHand> {

    private PokerEngine engine = PokerEngineFactory.getCurrentPokerEngine();
    private Set<Card> setOfCards = new HashSet<>();

    public PokerHand(String fiveCards) {

        if (fiveCards == null) {
            throw new InvalidPokerHandException("Five cards are null");
        }

        String[] cards = fiveCards.split(" ");

        if (cards.length != 5) {
            throw new InvalidPokerHandException("Poker hand size must be 5, was: " + cards.length);
        }

        populateSetOfCards(cards);

    }

    private void populateSetOfCards(String[] cards ) {

        for (String cardString : cards) {
            Card card = parseCard(cardString);
            setOfCards.add(card);
        }

        if(setOfCards.size() < 5) {
            throw new InvalidPokerHandException("Poker Hand contains duplicates!");
        }

    }

    /**
     * Converting a cardString into Card object which for easy manipulations.
     * @param cardString representing the card
     * @return Card object
     */
    private Card parseCard(String cardString) {
        if (cardString.length() != 2) {
            throw new InvalidCardException("Invalid Card ID: " + cardString);
        }

        String valueStringId = cardString.substring(0,1);
        String suitId = cardString.substring(1,2);

        CardValue cardValue = CardValue.findByCardValueId(valueStringId);

        if (cardValue == null) {
            throw new InvalidCardException("Invalid card value: " + valueStringId);
        }

        CardSuit cardSuit = CardSuit.findByCardSuitId(suitId);

        if (cardSuit == null) {
            throw new InvalidCardException("Invalid card suit: " + suitId);
        }

        return new Card(cardValue, cardSuit);
    }

    public Set<Card> getSetOfCards() {
        return setOfCards;
    }

    /**
     *  It's the requirement to implement compareTo in this class, so I have to initialize my PokerEngine here.
     *  The better solution would be to implement an external Comparator class -
     *  that way it will be much easier to decouple model and the logic.
     *  With the external logic it would be not hard to move all the logic and models into DI container
     *  such as Spring without complex AOP configuration.
     *
     * @param opponentHand to compare
     * @return result of the operation
     */
    @Override
    public int compareTo(PokerHand opponentHand) {

        PokerHandValue myValue = engine.getHandValue(this);
        PokerHandValue opponentValue = engine.getHandValue(opponentHand);

        if(myValue.getRank() != opponentValue.getRank()) {

            //If rank is higher - there is no need for further calculations
            return Integer.compare(myValue.getRank(), opponentValue.getRank());

        } else {
            //For the equal rank we have to compare combination values side by side
            //The sizes of value clusters must be equal for hands with the same rank.
            //PokerHandValue.getSortedValues contain values of different card combinations in the order of priority.
            for (int i = 0; i < myValue.getSortedValues().size(); i++) {

                CardValue myCardValue = myValue.getSortedValues().get(i);
                CardValue oppCardValue = opponentValue.getSortedValues().get(i);

                if ( myCardValue.cardValue != oppCardValue.cardValue ) {
                   return Integer.compare(myCardValue.cardValue, oppCardValue.cardValue);
                }

            }

        }

        return  HandResult.TIE.comparatorValue;

    }

}
