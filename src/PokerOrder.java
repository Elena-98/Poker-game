import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PokerOrder {

    private static final String VALUES = "23456789TJQKA";
    private static final String SUITS = "DHSC";
    private String[] cards;
    public PokerOrder (String[] cards){
        this.cards = cards;

    }
//    4H 4C 6S 7S KD
    private void combination(){
        List<Integer> values = new ArrayList<>();
        List<Character> suits = new ArrayList<>();
        int value;

        for (String card : cards){
            if (card.charAt(0) >= '2' && card.charAt(0)<='9'){
                value = card.charAt(0)  - '0';
            }else{
                switch(card.charAt(0)){
                    case 'A': value = 14; break;
                    case 'T': value = 10; break;
                    case 'J': value = 11; break;
                    case 'Q': value = 12; break;
                    case 'K': value = 13; break;
                    default: throw new IllegalArgumentException("Unknown card value: " + valueChar);
                }
            }
            values.add(value);
            suits.add(card.charAt(1));
        }
        Collections.sort(values);
        boolean consecutive = beConsecutive(values);






    }

    private boolean beConsecutive(List<Integer> values){

        for (int i = 0; i < values.size()-1; i++){
            if (values.get(i)+1 != values.get(i+1)){
                return false;
            }
        }
        return true;
    }

    private boolean beSameValue(){}

}
