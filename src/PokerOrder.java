import java.util.*;

public class PokerOrder {

    private static final String VALUES = "23456789TJQKA";
    private static final String SUITS = "DHSC";
    private String[] cards;
    private int rank = 1;
    public PokerOrder (String[] cards){
        this.cards = cards;

    }
//    4H 4C 6S 7S KD
    private void combination(){
        List<Integer> values = new ArrayList<>();
        Set<Character> suits = new HashSet<>();
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
        Collection<Integer> counts = valueCounts(values);

        if (consecutive){
//            the same suit
            if (suits.size()==1){
                if (values.get(0)==10){
                    rank = 10;
                }else{
                    rank =9;
                }
            }else{
                rank = 5;
            }
        }else{
            if (counts.contains(4)){
                rank = 8;
            }else if(counts.contains(3)){
                if (counts.contains(2)){
                    rank = 7;
                }else{
                    rank = 4;
                }
            }else if(counts.contains(2)){
                int pairCount = 0;
                for (int count: counts){
                    if (count==2){
                        pairCount++;
                    }
                }
                if (pairCount==2){
                    rank = 3;
                }else{
                    rank=2;
                }
            }
        }


    }

    private boolean beConsecutive(List<Integer> values){

        for (int i = 0; i < values.size()-1; i++){
            if (values.get(i)+1 != values.get(i+1)){
                return false;
            }
        }
        return true;
    }

    private Collection<Integer> valueCounts(List<Integer> values){
        Map<Integer,Integer> counts = new HashMap<>();
        for (int value : values){
            if(counts.containsKey(value)){
                counts.put(value,counts.get(value)+1);
            }else{
                counts.put(value,1);
            }
        }
        return counts.values();
    }

}
