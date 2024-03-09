import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PokerRank {

    public static void main (String[] args) throws FileNotFoundException {
        String filePath = "src/poker-hands.txt";
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }

        } catch (IOException e) {
            System.err.println("Failed to read file: " + e.getMessage());
        }
    }


}
