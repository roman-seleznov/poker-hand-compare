package rs.poker;

import rs.poker.exception.InvalidCardException;
import rs.poker.exception.InvalidPokerHandException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PokerHandTests {

    @Test(expected = InvalidPokerHandException.class)
    public void invalidHandSizeMinusOne() {
        new PokerHand("2H 3H 4H 5H");
    }

    @Test(expected = InvalidPokerHandException.class)
    public void invalidHandSizePlusOne() {
        new PokerHand("2H 3H 4H 5H AH KS");
    }

    @Test(expected = InvalidCardException.class)
    public void invalidCardUnknownValue() {
        new PokerHand("2H 3H 4H 5H ZH");
    }

    @Test(expected = InvalidCardException.class)
    public void invalidCardUnknownSuit() {
        new PokerHand("2H 3H 4H 5H KR");
    }

    @Test
    public void highest_straight_flush_wins() {
        compareHands(HandResult.LOSS, "2H 3H 4H 5H 6H", "KS AS TS QS JS");
    }

    @Test
    public void straight_flush_beats_4_of_a_kind() {
        compareHands(HandResult.WIN, "2H 3H 4H 5H 6H", "AS AD AC AH JD");
    }

    @Test
    public void highest_4_of_a_kind_wins() {
        compareHands(HandResult.WIN, "AS AH 2H AD AC", "JS JD JC JH 3D");
    }

    @Test
    public void four_of_a_kind_beats_a_full_house() {
        compareHands(HandResult.LOSS, "2S AH 2H AS AC", "JS JD JC JH AD");
    }

    @Test
    public void full_house_beats_a_flush() {
        compareHands(HandResult.WIN, "2S AH 2H AS AC", "2H 3H 5H 6H 7H");
    }

    @Test
    public void highest_flush_wins() {
        compareHands(HandResult.WIN, "AS 3S 4S 8S 2S", "2H 3H 5H 6H 7H");
    }

    @Test
    public void flush_beats_a_straight() {
        compareHands(HandResult.WIN, "2H 3H 5H 6H 7H", "2S 3H 4H 5S 6C");
    }

    @Test
    public void two_straights_with_same_highest_card_tie() {
        compareHands(HandResult.TIE, "2S 3H 4H 5S 6C", "3D 4C 5H 6H 2S");
    }

    @Test
    public void straight_beats_three_of_a_kind() {
        compareHands(HandResult.WIN, "2S 3H 4H 5S 6C", "AH AC 5H 6H AS");
    }

    @Test
    public void three_of_a_kind_beats_two_pairs() {
        compareHands(HandResult.LOSS, "2S 2H 4H 5S 4C", "AH AC 5H 6H AS");
    }

    @Test
    public void two_pairs_beats_a_single_pair() {
        compareHands(HandResult.WIN, "2S 2H 4H 5S 4C", "AH AC 5H 6H 7S");
    }

    @Test
    public void highest_pair_wins() {
        compareHands(HandResult.LOSS, "6S AD 7H 4S AS", "AH AC 5H 6H 7S");
    }

    @Test
    public void pair_beats_a_high_card() {
        compareHands(HandResult.LOSS, "2S AH 4H 5S KC", "AH AC 5H 6H 7S");
    }

    @Test
    public void lowest_card_loses() {
        compareHands(HandResult.LOSS, "2S 3H 6H 7S 9C", "7H 3C TH 6H 9S");
    }

    @Test
    public void highest_card_wins() {
        compareHands(HandResult.WIN, "4S 5H 6H TS AC", "3S 5H 6H TS AC");
    }

    @Test
    public void equal_cards_tie() {
        compareHands(HandResult.TIE, "2S AH 4H 5S 6C", "AD 4C 5H 6H 2C");
    }

    @Test
    public void two_full_houses_highest_three_of_a_kind_wins() {
        compareHands(HandResult.WIN, "KS KH KD 2S 2C", "JS JH JD AS AC");
    }
    
    private void compareHands(HandResult expectedResult, String playerHand, String opponentHand) {
        PokerHand player = new PokerHand(playerHand);
        PokerHand opponent = new PokerHand(opponentHand);
        int actualResult = player.compareTo(opponent);
        assertEquals(expectedResult.comparatorValue, actualResult);
    }
}

