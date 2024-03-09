import java.util.*;

public class PokerOrder {

    private List<String> cards;
    private List<Integer> values = new ArrayList<>();
    private Map<Integer,Integer> counts = new HashMap<>();
    private int rank = 1;
    public PokerOrder (List<String> cards){
        this.cards = cards;
        combination();
    }
//    4H 4C 6S 7S KD
    private void combination(){

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
                    default: throw new IllegalArgumentException("Unknown card value: " + card.charAt(0));
                }
            }
            values.add(value);
            suits.add(card.charAt(1));
        }
        Collections.sort(values);
        boolean consecutive = beConsecutive();
        Collection<Integer> counts = valueCounts();

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

    private boolean beConsecutive(){

        for (int i = 0; i < values.size()-1; i++){
            if (values.get(i)+1 != values.get(i+1)){
                return false;
            }
        }
        return true;
    }

    private Collection<Integer> valueCounts(){

        for (int value : values){
            if(counts.containsKey(value)){
                counts.put(value,counts.get(value)+1);
            }else{
                counts.put(value,1);
            }
        }
        return counts.values();
    }

    public int[] getRank(){
        switch (rank){
            case 10: return new int[]{10};
            case 9: return new int[]{9,values.get(0)};
            case 8:
                if (counts.get(values.get(0))==4){
                    return new int[]{8,values.get(0)};
                }else{
                    return new int[]{8,values.get(1)};
                }
//            three of a kind, pair
            case 7:
                if (counts.get(values.get(0))==3){
                    return new int[]{7,values.get(0),values.get(3)};
                }else{
                    return new int[]{7,values.get(2),values.get(0)};
                }
//            To do: update later in reverse order
            case 6: return reverseValues();
            case 5: return new int[]{5,values.get(0)};
//            three same value, one, one
            case 4:
                if (counts.get(values.get(0))==3){
                    return new int[]{4,values.get(0),values.get(4),values.get(3)};
                }else if (counts.get(values.get(1))==3){
                    return new int[]{4,values.get(1),values.get(4),values.get(0)};
                }else{
                    return new int[]{4,values.get(2),values.get(1),values.get(0)};
                }
//            pair,pair,one
            case 3:
                if (counts.get(values.get(0))==2){
                    if (counts.get(values.get(2))==2){
                        return new int[]{3,values.get(2),values.get(0),values.get(4)};
                    }else{
                        return new int[]{3,values.get(4),values.get(0),values.get(2)};
                    }

                }else{
                    return new int[]{3,values.get(4),values.get(1),values.get(0)};
                }
//            To do: values, reverse the order
            case 2:
                return reverseValues();
            case 1:
                return reverseValues();
        }

        return new int[0];
    }

    private int[] reverseValues(){
        int[] reverse;
        switch (rank){
            case 1:
            case 6:
                reverse= new int[6];
                reverse[0] = rank;
                for (int i=0;i<5;i++){
                    reverse[i+1] = values.get(4-i);
                }
                return reverse;
//          To do: return pair value, reverse order
            case 2:
                reverse= new int[5];
                return new int[]{2};

        }


        return new int[0];
    }


}
