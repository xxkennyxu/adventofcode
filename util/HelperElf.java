import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public final class HelperElf {
    public static BufferedReader openInputFile(int day, int year) throws FileNotFoundException {
        File file = new File(System.getProperty("user.dir") + "\\" + year + "\\" + "Day " + day + "\\input.txt");
        return new BufferedReader(new FileReader(file));
    }

    public static BufferedReader openTestFile(int day, int year) throws FileNotFoundException {
        File file = new File(System.getProperty("user.dir") + "\\" + year + "\\" + "Day " + day + "\\test.txt");
        return new BufferedReader(new FileReader(file));
    }

    public static List<String> getAllLinesFromInputFile(int day, int year) throws IOException {
        return Files.lines(Paths.get(System.getProperty("user.dir") + "\\" + year + "\\" + "Day " + day + "\\input.txt")).toList();
    }

    public static List<String> getAllLinesFromTestFile(int day, int year) throws IOException {
        return Files.lines(Paths.get(System.getProperty("user.dir") + "\\" + year + "\\" + "Day " + day + "\\test.txt")).toList();
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
