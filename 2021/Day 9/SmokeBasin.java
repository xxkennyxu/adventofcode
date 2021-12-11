import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SmokeBasin {

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = HelperElf.openFile(9, 2021);

        List<List<Integer>> heightMap = new ArrayList<>();

        String st;
        while ((st = bufferedReader.readLine()) != null) {
            heightMap.add(Arrays.stream(st.split("")).map(Integer::parseInt).collect(Collectors.toList()));
        }

        List<Integer> basinSizes = new ArrayList<>();
        for (int i = 0; i < heightMap.size(); i++) {
            for (int j = 0; j < heightMap.get(i).size(); j++){
                if (isLowPoint(heightMap, i, j)) {
                    basinSizes.add(1 + getBasinSize(heightMap, i, j));
                }
            }
        }
        basinSizes.sort((o1, o2) -> {
            if (o1 > o2) return -1;
            else if (o1.equals(o2)) return 0;
            else return 1;
        });
        System.out.println(basinSizes.get(0) * basinSizes.get(1) * basinSizes.get(2));
    }

    public static boolean isLowPoint(List<List<Integer>> heightMap, int x, int y) {
        final int currentValue = heightMap.get(x).get(y);
        if (x != 0) {
            if (heightMap.get(x - 1).get(y) <= currentValue) {
                return false;
            }
        }
        if (x != heightMap.size() - 1) {
            if (heightMap.get(x + 1).get(y) <= currentValue) {
                return false;
            }
        }
        if (y != 0) {
            if (heightMap.get(x).get(y - 1) <= currentValue) {
                return false;
            }
        }
        if (y != heightMap.get(x).size() - 1) {
            if (heightMap.get(x).get(y + 1) <= currentValue) {
                return false;
            }
        }
        return true;
    }

    public static int getBasinSize(List<List<Integer>> heightMap, int x, int y) {
        if (heightMap.get(x).get(y) == 9) {
            return 0;
        }

        int currentValue = heightMap.get(x).get(y);
        int basinSize = 0;
        heightMap.get(x).set(y, -1); // visited, too lazy to create Node class
        if (x != 0 && heightMap.get(x - 1).get(y) != 9 && heightMap.get(x - 1).get(y) > currentValue) {
            basinSize += 1 + getBasinSize(heightMap, x - 1, y);
        }
        if (x != heightMap.size() - 1 && heightMap.get(x + 1).get(y) != 9 && heightMap.get(x + 1).get(y) > currentValue) {
            basinSize += 1 + getBasinSize(heightMap, x + 1, y);
        }
        if (y != 0 && heightMap.get(x).get(y - 1) != 9 &&  heightMap.get(x).get(y - 1) > currentValue) {
            basinSize += 1 + getBasinSize(heightMap, x, y - 1);
        }
        if (y != heightMap.get(x).size() - 1 && heightMap.get(x).get(y + 1) != 9 &&  heightMap.get(x).get(y + 1) > currentValue) {
            basinSize += 1 + getBasinSize(heightMap, x, y + 1);
        }
        return basinSize;
    }
}
