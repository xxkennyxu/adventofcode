import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class DepthMeasurements {

    public static void main(String[] args) throws Exception {
        File file = new File("input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

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
