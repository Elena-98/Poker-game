import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PokerRank {
    private static int wins1 = 0;
    private static int wins2 = 0;
    public static void main (String[] args) throws FileNotFoundException {
//        String filePath = "src/poker-hands-testing.txt";
        String filePath = "src/poker-hands.txt";
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = reader.readLine()) != null){
                List<String> twoCards = Arrays.asList(line.split(" "));
                List<String> player1Cards = twoCards.subList(0, 5);
                List<String> player2Cards = twoCards.subList(5, 10);

                PokerOrder player1 = new PokerOrder(player1Cards);
                PokerOrder player2 = new PokerOrder(player2Cards);

                int[] player1Result = player1.getRank();
                int[] player2Result = player2.getRank();

                PokerRank a = new PokerRank(player1Result,player2Result);

            }

        } catch (IOException e) {
            System.err.println("Failed to read file: " + e.getMessage());
        }
        System.out.println("wins:"+wins1);
        System.out.println("wins:"+wins2);
    }

    public PokerRank(int[] player1Result,int[] player2Result){
        compareRank(player1Result,player2Result);

    }


    private void compareRank(int[] player1Result,int[] player2Result){
        if (player1Result[0]>player2Result[0]){
            wins1++;

        }else if (player1Result[0]==player2Result[0]){
            compareValue(player1Result,player2Result);
        }else{
            wins2++;
        }
//        System.out.println("player1: "+player1Result[0]+"    player2: "+player2Result[0]);
    }

    private void compareValue(int[] player1Result,int[] player2Result){

        for (int i = 1; i<player1Result.length; i++){

            if (player1Result[i]>player2Result[i]){
                wins1++;
                break;
            }else if(player1Result[i]<player2Result[i]){
                wins2++;
                break;
            }

        }
    }


}
