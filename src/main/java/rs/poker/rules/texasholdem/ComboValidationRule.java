package rs.poker.rules.texasholdem;

import rs.poker.CardValue;
import rs.poker.PokerHandValue;
import rs.poker.rules.AbstractPokerHandValuationRule;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public class ComboValidationRule extends AbstractPokerHandValuationRule {

    @Override
    protected PokerHandValue calculateRuleValue() {

        if (valueMap.size() < 5) {

            CardValue fourOfaKindValue = null;
            CardValue threeOfaKindValue = null;
            CardValue pairValue = null;
            CardValue secondPairValue = null;

            PokerHandValue value = new PokerHandValue();

            SortedSet<CardValue> sortedSet = new TreeSet<>();

            for ( CardValue cardValue : valueMap.keySet() ) {
                if( valueMap.get(cardValue).size() == 1 ){
                    sortedSet.add(cardValue);
                } else if ( valueMap.get(cardValue).size() == 2 ) {

                    if (pairValue == null) {
                        pairValue = cardValue;
                    } else {
                        secondPairValue = pairValue;
                    }

                } else if ( valueMap.get(cardValue).size() == 3 ) {
                    threeOfaKindValue = cardValue;
                } else if ( valueMap.get(cardValue).size() == 4 ) {
                   fourOfaKindValue = cardValue;
                }
            }

            Collections.reverse(value.getSortedValues());
            sortedSet.forEach(cardValue -> value.getSortedValues().add(cardValue));

            populateValue(fourOfaKindValue, threeOfaKindValue, pairValue, secondPairValue, value);

            return value;

        }

        return null;
    }

    private void populateValue(CardValue fourOfaKindValue,
                               CardValue threeOfaKindValue,
                               CardValue pairValue,
                               CardValue secondPairValue,
                               PokerHandValue pokerHandValue) {

        if (fourOfaKindValue != null) {

            pokerHandValue.setRank(PokerHandRank.FOUROFAKIND.rank);
            pokerHandValue.getSortedValues().add(0,fourOfaKindValue);

        } else if (threeOfaKindValue != null) {

            pokerHandValue.getSortedValues().add(0, threeOfaKindValue);

            if (pairValue != null) {
                pokerHandValue.setRank(PokerHandRank.FULLHOUSE.rank);
                pokerHandValue.getSortedValues().add(1, pairValue);
            } else {
                pokerHandValue.setRank(PokerHandRank.THREEOFAKIND.rank);
            }

        } else if (pairValue != null) {

            if (secondPairValue != null) {
                pokerHandValue.setRank(PokerHandRank.TWOPAIRS.rank);

                if (secondPairValue.cardValue > pairValue.cardValue) {
                    pokerHandValue.getSortedValues().add(0, secondPairValue);
                    pokerHandValue.getSortedValues().add(1, pairValue);
                } else {
                    pokerHandValue.getSortedValues().add(0, pairValue);
                    pokerHandValue.getSortedValues().add(1, secondPairValue);
                }

            } else {
                pokerHandValue.setRank(PokerHandRank.PAIR.rank);
                pokerHandValue.getSortedValues().add(0, pairValue);
            }

        }
    }

}
