package rs.poker.rules.texasholdem;

public enum PokerHandRank {

    HIGHCARD(100),
    PAIR(200),
    TWOPAIRS(300),
    THREEOFAKIND(400),
    STRAIGHT(500),
    FLUSH(600),
    FULLHOUSE(700),
    FOUROFAKIND(800),
    STRAIGHTFLUSH(900),
    ROYALFLUSH(1000);

    public final int rank;

    PokerHandRank(int rank) {
        this.rank = rank;
    }


}
