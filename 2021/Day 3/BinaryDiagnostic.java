import java.io.BufferedReader;
import java.io.IOException;

public class BinaryDiagnostic {

    public static void main(String[] args) throws IOException {
        BufferedReader br = HelperElf.openInputFile(3, 2021);

        String st;
        int numberCount = 0; // since gamma and epsilon are mutually exclusive, we can invert gamma or epsilon
        int[] onesBitCounter = null; // only count for 1s
        while ((st = br.readLine()) != null) {
            if (onesBitCounter == null) {
                onesBitCounter = new int[st.length()]; // input binary number length is not the same as sample
            }
            numberCount++;

            for (int i = 0; i < st.length(); i++) {
                if (st.charAt(i) == '1') {
                    onesBitCounter[i]++;
                }
            }
        }

        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();
        for (int numOnes : onesBitCounter) {
            int numZeros = numberCount - numOnes;

            if (numOnes > numZeros) {
                gamma.append("1");
                epsilon.append("0");
            } else if (numZeros > numOnes) {
                gamma.append("0");
                epsilon.append("1");
            } else {
                // if an even amount of 1/0 bits, requirements are insufficient
                throw new RuntimeException("1-bits: " + numOnes + " 0-bits: " + numZeros);
            }
        }
        int gammaDecimal = HelperElf.convertBinaryToDecimal(gamma.toString());
        int epsilonDecimal = HelperElf.convertBinaryToDecimal(epsilon.toString());
        System.out.println(gamma);
        System.out.println(gammaDecimal);
        System.out.println(epsilon);
        System.out.println(epsilonDecimal);
        System.out.println("Result: " + gammaDecimal * epsilonDecimal);
    }
}
