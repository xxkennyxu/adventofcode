import java.io.BufferedReader;
import java.util.Arrays;

public class WindowedDepthMeasurements {

    public static void main(String[] args) throws Exception {
        BufferedReader br = HelperElf.openFile(1, 2021);

        String st;
        int[] window = new int[3];
        int windowIndex = 0;
        int count = 0;
        int previousWindow = Integer.MAX_VALUE;
        while ((st = br.readLine()) != null) {
            window[windowIndex % 3] = Integer.parseInt(st);
            windowIndex++;

            if (windowIndex > 2) {
                int currentWindow = Arrays.stream(window).reduce(0, Integer::sum);
                if (previousWindow < currentWindow) {
                    count++;
                }
                previousWindow = currentWindow;
            }
        }
        System.out.println(count);
    }
}
