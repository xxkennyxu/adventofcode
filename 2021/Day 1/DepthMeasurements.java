import java.io.BufferedReader;

public class DepthMeasurements {

    public static void main(String[] args) throws Exception {
        BufferedReader br = InputReader.open(1, 2021);

        String st;
        int previous = Integer.MIN_VALUE;
        int count = 0;
        while ((st = br.readLine()) != null) {
            final int current = Integer.parseInt(st);
            if (previous != Integer.MIN_VALUE) {
                if (previous < current) {
                    count++;
                }
            }
            previous = current;
        }
        System.out.println(count);
    }
}
