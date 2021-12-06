import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public final class HelperElf {
    public static BufferedReader openFile(int day, int year) throws FileNotFoundException {
        File file = new File(System.getProperty("user.dir") + "\\" + year + "\\" + "Day " + day + "\\input.txt");
        return new BufferedReader(new FileReader(file));
    }

    public static int convertBinaryToDecimal(String binary) {
        int decimal = 0;
        int exponent = 0;
        for (int i = binary.length() - 1; i >= 0; i--) {
            if (binary.charAt(i) == '1') {
                decimal += Math.pow(2, exponent);
            }
            exponent++;
        }
        return decimal;
    }
}
