import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PokerRank {

    public static void main (String[] args) throws FileNotFoundException {
        String filePath = "src/poker-hands.txt";
        int wins = 0;
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

                if (player1Result[0]>player2Result[0]){
                    wins++;
                }
            }

        } catch (IOException e) {
            System.err.println("Failed to read file: " + e.getMessage());
        }
        System.out.println(wins);
    }


}
