package rs.poker;

import java.util.LinkedList;
import java.util.List;

/**
 * Holds a total value of the PokerHand to compare;
 */
public class PokerHandValue {

    private int rank;
    private List<CardValue> sortedValues = new LinkedList<>();

    public PokerHandValue () {
    }

    public List<CardValue> getSortedValues() {
        return sortedValues;
    }

    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }

}
