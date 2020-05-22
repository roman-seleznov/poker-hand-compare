package rs.poker;

import java.util.Objects;

public class Card implements Comparable<Card> {

    private CardValue cardValue;
    private CardSuit cardSuit;

    public Card(CardValue cardValue, CardSuit cardSuit) {
        this.cardValue = cardValue;
        this.cardSuit = cardSuit;
    }

    public CardValue getCardValue() {
        return cardValue;
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return cardValue == card.cardValue &&
                cardSuit == card.cardSuit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardValue, cardSuit);
    }

    @Override
    public int compareTo(Card other) {
        return Integer.compare(cardValue.cardValue, other.cardValue.cardValue);
    }
}
