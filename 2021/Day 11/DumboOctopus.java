import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DumboOctopus extends AOCProblem {
    @Override
    int getDay() {
        return 11;
    }

    @Override
    int getYear() {
        return 2021;
    }

    @Override
    String part1(List<String> input) {
        List<List<Integer>> octopusMap = new ArrayList<>();

        for (String line : input) {
            octopusMap.add(Arrays.stream(line.split("")).map(Integer::parseInt).collect(Collectors.toList()));
        }

        int numFlashes = 0;
        for (int days = 0; days < 100; days++) {
            for (int i = 0; i < octopusMap.size(); i++) {
                for (int j = 0; j < octopusMap.get(i).size(); j++) {

                    if (incrementEnergy(octopusMap, i, j) > 9) {
                        numFlashes += flash(octopusMap, i, j);
                    }
                }
            }

            for (int i = 0; i < octopusMap.size(); i++) {
                for (int j = 0; j < octopusMap.get(i).size(); j++) {
                    if (octopusMap.get(i).get(j) == -1) {
                        octopusMap.get(i).set(j, 0);
                    }
                }
            }
        }
        return String.valueOf(numFlashes);
    }

    @Override
    String part2(List<String> input) {
        List<List<Integer>> octopusMap = new ArrayList<>();

        for (String line : input) {
            octopusMap.add(Arrays.stream(line.split("")).map(Integer::parseInt).collect(Collectors.toList()));
        }

        int numFlashes = octopusMap.size() * octopusMap.get(0).size(); // assuming fixed row length
        int numDays = 1;
        while (numFlashes != 0) {
            for (int i = 0; i < octopusMap.size(); i++) {
                for (int j = 0; j < octopusMap.get(i).size(); j++) {
                    if (incrementEnergy(octopusMap, i, j) > 9) {
                        flash(octopusMap, i, j);
                    }
                }
            }

            for (int i = 0; i < octopusMap.size(); i++) {
                for (int j = 0; j < octopusMap.get(i).size(); j++) {
                    if (octopusMap.get(i).get(j) == -1) {
                        numFlashes--;
                        octopusMap.get(i).set(j, 0);
                    }
                }
            }

            if (numFlashes != 0) {
                numFlashes = octopusMap.size() * octopusMap.get(0).size(); // assuming fixed row length
                numDays++;
            }
        }
        return String.valueOf(numDays);
    }


    private int flash(List<List<Integer>> octopusMap, int i, int j) {
        octopusMap.get(i).set(j, -1);
        int numFlashes = 1;

        // North
        if (i != 0 && incrementEnergy(octopusMap, i - 1, j) > 9) {
            numFlashes += flash(octopusMap, i - 1, j);
        }
        // East
        if (j != octopusMap.get(i).size() - 1 && incrementEnergy(octopusMap, i, j + 1) > 9) {
            numFlashes += flash(octopusMap, i, j + 1);
        }
        // South
        if (i != octopusMap.size() - 1 && incrementEnergy(octopusMap, i + 1, j) > 9) {
            numFlashes += flash(octopusMap, i + 1, j);
        }
        // West
        if (j != 0 && incrementEnergy(octopusMap, i, j - 1) > 9) {
            numFlashes += flash(octopusMap, i, j - 1);
        }
        // North East
        if (i != 0 && j != octopusMap.get(i).size() - 1 && incrementEnergy(octopusMap, i - 1, j + 1) > 9) {
            numFlashes += flash(octopusMap, i - 1, j + 1);
        }
        // North West
        if (i != 0 && j != 0 && incrementEnergy(octopusMap, i - 1, j - 1) > 9) {
            numFlashes += flash(octopusMap, i - 1, j - 1);
        }
        // South East
        if (i != octopusMap.size() - 1 && j != octopusMap.get(i).size() - 1 && incrementEnergy(octopusMap, i + 1, j + 1) > 9) {
            numFlashes += flash(octopusMap, i + 1, j + 1);
        }
        // South West
        if (i != octopusMap.size() - 1 && j != 0 && incrementEnergy(octopusMap, i + 1, j - 1) > 9) {
            numFlashes += flash(octopusMap, i + 1, j - 1);
        }
        return numFlashes;
    }

    private int incrementEnergy(List<List<Integer>> octopusMap, int i, int j) {
        if (octopusMap.get(i).get(j) == -1) return -1; // visited

        int previous = octopusMap.get(i).set(j, octopusMap.get(i).get(j) + 1);
        return previous + 1;
    }

    public static void main(String[] args) throws IOException {
        new DumboOctopus().run();
    }
}
