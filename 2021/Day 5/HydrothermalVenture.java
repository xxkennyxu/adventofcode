import java.io.BufferedReader;
import java.util.Arrays;
import java.util.List;

public class HydrothermalVenture {

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = HelperElf.openFile(5, 2021);

        int[][] hydrothermalMap = new int[1000][1000];
        String st;
        while ((st = bufferedReader.readLine()) != null) {
            String[] fromTo = st.split(" -> ");
            List<Integer> x1y1 = Arrays.stream(fromTo[0].split(",")).map(Integer::parseInt).toList();
            List<Integer> x2y2 = Arrays.stream(fromTo[1].split(",")).map(Integer::parseInt).toList();

            int x1 = x1y1.get(0);
            int y1 = x1y1.get(1);
            int x2 = x2y2.get(0);
            int y2 = x2y2.get(1);
            int xDistance = Math.abs(x2-x1);
            int yDistance = Math.abs(y2-y1);

            if (x1 == x2) {
                for (int i = Math.min(y1, y2); i <= Math.max(y1, y2); i++) {
                    hydrothermalMap[x1][i]++;
                }
            } else if (y1 == y2) {
                for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
                    hydrothermalMap[i][y1]++;
                }
            } else if (xDistance == yDistance) {
                // x1 -> x2
                    // x1 < x2 = +
                    // x1 > x2 = -
                // y1 -> y2
                    // y1 < y2 = +
                    // y1 > y2 = -
                if (x1 < x2) {
                    if (y1 < y2) {
                        // +,+
                        for (int i = 0; i <= xDistance; i++) {
                            hydrothermalMap[x1 + i][y1 + i]++;
                        }
                    } else {
                        // +,-
                        for (int i = 0; i <= xDistance; i++) {
                            hydrothermalMap[x1 + i][y1 - i]++;
                        }
                    }
                } else {
                    if (y1 < y2) {
                        // -,+
                        for (int i = 0; i <= xDistance; i++) {
                            hydrothermalMap[x1 - i][y1 + i]++;
                        }
                    } else {
                        // -,-
                        for (int i = 0; i <= xDistance; i++) {
                            hydrothermalMap[x1 - i][y1 - i]++;                        }
                    }
                }
            }
        }

        int twoOrLarger = 0;
        for (int[] row : hydrothermalMap) {
            for (int j : row) {
                if (j >= 2) {
                    twoOrLarger++;
                }
            }
        }
        System.out.println(twoOrLarger);
    }
}
