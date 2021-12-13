import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SevenSegmentSearch {

    static class SevenSegmentDisplay {
        HashMap<String, Integer> stringNumberMap;
        String one, four, seven;
        int outputValue;

        public SevenSegmentDisplay(String[] uniqueInput, String[] fourDigitOutput) {
            this.stringNumberMap = new HashMap<>();

            processUniqueInput(uniqueInput);
            processFourDigitOutput(fourDigitOutput);
        }

        private void processUniqueInput(String[] uniqueInput) {
            List<String> notUniqueNumberedInputs = new ArrayList<>();
            for (String input : uniqueInput) {
                char[] chars = input.toCharArray();
                Arrays.sort(chars);
                String sortedInput = new String(chars);
                if (input.length() == 2) { // 1
                    one = sortedInput;
                    stringNumberMap.put(sortedInput, 1);
                } else if (input.length() == 4) { // 4
                    four = sortedInput;
                    stringNumberMap.put(sortedInput, 4);
                } else if (input.length() == 3) { // 7
                    seven = sortedInput;
                    stringNumberMap.put(sortedInput, 7);
                } else if (input.length() == 7) { // 8
                    stringNumberMap.put(sortedInput, 8);
                } else {
                    notUniqueNumberedInputs.add(sortedInput);
                }
            }

            for (String notUnique : notUniqueNumberedInputs) {
                int onesMatch = 0;
                int foursMatch = 0;
                int sevensMatch = 0;

                for (char c : notUnique.toCharArray()) {
                    if (one.contains(String.valueOf(c))) {
                        onesMatch++;
                    }
                    if (four.contains(String.valueOf(c))) {
                        foursMatch++;
                    }
                    if (seven.contains(String.valueOf(c))) {
                        sevensMatch++;
                    }
                }

                int targetNumber = -1;
                if (onesMatch == 2 && foursMatch == 3 && sevensMatch == 3) {
                    if (notUnique.length() == 6) {
                        // 0 = 6 (shares two segments with 1, shares three with 4, shares 3 with 7) 2-3-3
                        targetNumber = 0;
                    } else {
                        // 3 = 5 (shares two segments with 1, share three segments with 4, shares three segments with 7) 2-3-3
                        targetNumber = 3;
                    }

                } else if (onesMatch == 1 && foursMatch == 2 && sevensMatch == 2) {
                    // 2 = 5 (shares one segment with 1, shares two segment with 4, shares two segments with 7) 1-2-2
                    targetNumber = 2;
                } else if (onesMatch == 2 && foursMatch == 4 && sevensMatch == 3) {
                    // 9 = 6 (shares two segments with 1, shares four segments with 4, shares three segments with 7) [2-4-3]
                    targetNumber = 9;
                } else if (onesMatch == 1 && foursMatch == 3 &&  sevensMatch == 2) {
                    if (notUnique.length() == 5) {
                        // 5 = 5 (shares one segment with 1, shares three segments with 4, shares two segments with 7) 1-3-2
                        targetNumber = 5;
                    } else {
                        // 6 = 6 (shares one segment with 1, shares three segments with 4, shares two segments with 7) 1-3-2
                        targetNumber = 6;
                    }
                }

                stringNumberMap.put(notUnique, targetNumber);
            }
        }

        private void processFourDigitOutput(String[] fourDigitOutput) {
            StringBuilder finalOutput = new StringBuilder();
            for (String output : fourDigitOutput) {
                char[] chars = output.toCharArray();
                Arrays.sort(chars);
                String sortedOutput = new String(chars);

                finalOutput.append(stringNumberMap.get(sortedOutput));
            }
            this.outputValue = Integer.parseInt(finalOutput.toString());
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = HelperElf.openInputFile(8, 2021);

        List<SevenSegmentDisplay> sevenSegmentDisplays = new ArrayList<>();
        String st;
        while ((st = bufferedReader.readLine()) != null) {
            String[] uniqueSignalPatterns = st.split(" \\| ")[0].split(" ");
            String[] fourDigitOutputValue = st.split(" \\| ")[1].split(" ");
            sevenSegmentDisplays.add(new SevenSegmentDisplay(uniqueSignalPatterns, fourDigitOutputValue));
        }

        int numbers = 0;
        for (SevenSegmentDisplay sevenSegmentDisplay : sevenSegmentDisplays) {
            numbers += sevenSegmentDisplay.outputValue;
        }
        System.out.println(numbers);
    }
}
