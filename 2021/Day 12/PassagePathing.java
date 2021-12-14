import java.io.IOException;
import java.util.*;
import java.util.function.Function;

public class PassagePathing extends AOCProblem {
    @Override
    int getDay() {
        return 12;
    }

    @Override
    int getYear() {
        return 2021;
    }

    @Override
    int getNumberOfTestInputs() {
        return 3;
    }

    @Override
    String part1(List<String> input) {
        Map<String, Cave> caveMap = setupCaveMap(input);
        return String.valueOf(findPath(caveMap.get("start"), new ArrayList<>(), false));
    }

    @Override
    String part2(List<String> input) {
        Map<String, Cave> caveMap = setupCaveMap(input);
        return String.valueOf(findPath(caveMap.get("start"), new ArrayList<>(), true));
    }

    private Map<String, Cave> setupCaveMap(List<String> input) {
        Map<String, Cave> caveMap = new HashMap<>();

        for (String line : input) {
            String[] fromTo = line.split("-");

            Cave fromCave, toCave;
            if (caveMap.containsKey(fromTo[0])) {
                fromCave = caveMap.get(fromTo[0]);
            } else {
                fromCave = new Cave(fromTo[0], Character.isLowerCase(fromTo[0].toCharArray()[0]));
                caveMap.put(fromTo[0], fromCave);
            }

            if (caveMap.containsKey(fromTo[1])) {
                toCave = caveMap.get(fromTo[1]);
            } else {
                toCave = new Cave(fromTo[1], Character.isLowerCase(fromTo[1].toCharArray()[0]));
                caveMap.put(fromTo[1], toCave);
            }

            // prevent infinite loops between start/end and other nodes
            if (!fromCave.caveName.equals("end") && !toCave.caveName.equals("start")) {
                fromCave.addConnection(toCave);
            }
            if (!fromCave.caveName.equals("start") && !toCave.caveName.equals("end")) {
                toCave.addConnection(fromCave);
            }
        }
        return caveMap;
    }

    private int findPath(Cave currentCave, List<String> currentPath, boolean isPartTwo) {
        currentPath.add(currentCave.caveName);
        if (currentCave.caveName.equals("end")) {
            return 1;
        }

        int numPaths = 0;
        for (Cave connectingCave : currentCave.connectingCaves) {
            int caveCountInPath = contains(currentPath, connectingCave.caveName);
            if (!connectingCave.isSmall || caveCountInPath == 0) {
                numPaths += findPath(connectingCave, new ArrayList<>(currentPath), isPartTwo);
            }
            else if (isPartTwo && caveCountInPath == 1 && !hasVisitedTwoSmallCaves(currentPath)) {
                numPaths += findPath(connectingCave, new ArrayList<>(currentPath), isPartTwo);
            }
        }
        return numPaths;
    }

    private int contains(List<String> path, String caveName) {
        int count = 0;
        for (String p : path) {
            if (p.equals(caveName)) {
                count++;
            }
        }
        return count;
    }

    private boolean hasVisitedTwoSmallCaves(List<String> path) {
        Map<String, Integer> caveCount = new HashMap<>();
        for (String p : path) {
            if (Character.isLowerCase(p.toCharArray()[0])) {
                caveCount.put(p, caveCount.containsKey(p) ? caveCount.get(p) + 1 : 1);
            }
        }
        List<Integer> countList = caveCount.values().stream().sorted().toList();
        return countList.get(countList.size() - 1) == 2;
    }

    static class Cave {
        String caveName;
        boolean isSmall;
        boolean visited;
        List<Cave> connectingCaves;

        public Cave(String caveName, boolean isSmall) {
            if (caveName.equals("end") || caveName.equals("start")) {
                this.isSmall = false;
            } else {
                this.isSmall = isSmall;
            }
            this.caveName = caveName;
            this.visited = false;

            connectingCaves = new ArrayList<>();
        }

        void addConnection(Cave connectingCave) {
            connectingCaves.add(connectingCave);
        }

        public String toString() {
            return caveName;
        }
    }

    public static void main(String[] args) throws IOException {
        new PassagePathing().run();
    }
}
