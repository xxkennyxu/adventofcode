import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public final class InputReader {
    public static BufferedReader open(int day, int year) throws FileNotFoundException {
        File file = new File(System.getProperty("user.dir") + "\\" + year + "\\" + "Day " + day + "\\input.txt");
        return new BufferedReader(new FileReader(file));
    }
}
