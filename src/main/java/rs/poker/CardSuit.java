package rs.poker;

import java.util.Arrays;

/**
 * The second character represents the suit: S(pades), H(earts), D(iamonds), C(lubs)
 */
public enum CardSuit {

    SPADES("S"),
    HEARTS("H"),
    DIAMONDS("D"),
    CLUBS("C");

    public final String suitId;

    CardSuit(String suitId) {
        this.suitId = suitId;
    }

    public static CardSuit findByCardSuitId(final String cardSuitId){
        return Arrays.stream(values()).filter(value -> value.suitId.equals(cardSuitId)).findFirst().orElse(null);
    }

}
