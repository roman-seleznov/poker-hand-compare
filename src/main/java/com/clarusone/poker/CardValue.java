package com.clarusone.poker;

import java.util.Arrays;

/**
 * The first character is the value of the card:
 * 2, 3, 4, 5, 6, 7, 8, 9, T(en), J(ack), Q(ueen), K(ing), A(ce)
 */
public enum CardValue {

    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE( "5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("T", 10),
    JACK("J", 11),
    QUEEN("Q", 12),
    KING("K", 13),
    ACE("A", 14);

    public final String cardValueId;
    public final int cardValue;

    CardValue(String cardValueId, int cardValue) {
        this.cardValueId = cardValueId;
        this.cardValue = cardValue;
    }

    public static CardValue findByCardValueId(final String cardValueId){
        return Arrays.stream(values()).filter(value -> value.cardValueId.equals(cardValueId)).findFirst().orElse(null);
    }

}
