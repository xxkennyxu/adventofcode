import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.BiFunction;

public class OxygenCO2Rating {

    public static void main(String[] args) throws IOException {
        BufferedReader br = HelperElf.openFile(3, 2021);

        String st;
        ArrayList<String> oneBitPosition = new ArrayList<>();
        ArrayList<String> zeroBitPosition = new ArrayList<>();

        while ((st = br.readLine()) != null) {
            if (st.charAt(0) == '1') {
                oneBitPosition.add(st);
            } else {
                zeroBitPosition.add(st);
            }
        }

        String binaryOxygenReading = findReading(
                oneBitPosition,
                zeroBitPosition,
                (onesList, zerosList) -> onesList.size() >= zerosList.size(),
                (onesList, zerosList) -> zerosList.size() == 0 || (onesList.size() == 1 && zerosList.size() == 1),
                (onesList, zerosList) -> onesList.size() == 0,
                1);
        int decimalOxygenReading = HelperElf.convertBinaryToDecimal((binaryOxygenReading));
        System.out.println(binaryOxygenReading);
        System.out.println(decimalOxygenReading);

        String binaryCO2Reading = findReading(
                oneBitPosition,
                zeroBitPosition,
                (onesList, zerosList) -> onesList.size() < zerosList.size(),
                (onesList, zerosList) -> zerosList.size() == 0,
                (onesList, zerosList) -> onesList.size() == 0 || (zerosList.size() == 1 && onesList.size() == 1),
                1);
        int decimalCO2Reading = HelperElf.convertBinaryToDecimal((binaryCO2Reading));
        System.out.println(binaryCO2Reading);
        System.out.println(decimalCO2Reading);
        System.out.println("Result: " + decimalCO2Reading * decimalOxygenReading);
    }

    private static String findReading(ArrayList<String> oneBitPosition,
                                      ArrayList<String> zeroBitPosition,
                                      BiFunction<ArrayList<String>, ArrayList<String>, Boolean> readingCondition,
                                      BiFunction<ArrayList<String>, ArrayList<String>, Boolean> baseCase,
                                      BiFunction<ArrayList<String>, ArrayList<String>, Boolean> baseCase2,
                                      int bitCriteriaPosition) {
        if (baseCase.apply(oneBitPosition, zeroBitPosition)) {
            return oneBitPosition.get(0);
        } else if (baseCase2.apply(oneBitPosition, zeroBitPosition)) {
            return zeroBitPosition.get(0);
        }

        ArrayList<String> newOneBitPosition = new ArrayList<>();
        ArrayList<String> newZeroBitPosition = new ArrayList<>();

        if (readingCondition.apply(oneBitPosition, zeroBitPosition)) {
            for (String st : oneBitPosition) {
                if (st.charAt(bitCriteriaPosition) == '1') {
                    newOneBitPosition.add(st);
                } else {
                    newZeroBitPosition.add(st);
                }
            }
        } else {
            for (String st : zeroBitPosition) {
                if (st.charAt(bitCriteriaPosition) == '1') {
                    newOneBitPosition.add(st);
                } else {
                    newZeroBitPosition.add(st);
                }
            }
        }
        return findReading(newOneBitPosition, newZeroBitPosition, readingCondition, baseCase, baseCase2,bitCriteriaPosition + 1);
    }
}
