package rs.poker.engine;

/**
 * This factory may be extended in order to support a different PokerEngines
 */
public class PokerEngineFactory {

    private static PokerEngine currentPokerEngine;

    public static PokerEngine getCurrentPokerEngine() {

        if (currentPokerEngine == null) {
            currentPokerEngine = new TexasHoldemEngine();
        }

        return currentPokerEngine;
    }


}
