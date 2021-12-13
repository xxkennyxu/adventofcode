import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WhaleAttack {

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = HelperElf.openInputFile(7, 2021);

        List<Integer> crabPositions = new ArrayList<>();

        String st;
        while ((st = bufferedReader.readLine()) != null) {
            crabPositions = Arrays.stream(st.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        }
        crabPositions.sort((o1, o2) -> {
            if (o1.equals(o2)) return 0;
            return o1 < o2 ? -1 : 1;
        });

        int previous = Integer.MAX_VALUE;
        for (int i = 0; i < crabPositions.get(crabPositions.size() - 1); i++) {
            previous = Math.min(previous, fuelCalculation(i, crabPositions));
        }

        System.out.println(previous);
    }

    private static int fuelCalculation(int location, List<Integer> crabPositions) {
        int fuelRequired = 0;
        for (Integer pos : crabPositions) {
            int distance = Math.abs(location - pos);
            for (int i = distance; i > 0; i--) {
                fuelRequired += i;
            }
        }
        return fuelRequired;
    }
}
