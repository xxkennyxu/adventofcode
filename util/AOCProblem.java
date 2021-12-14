import java.io.IOException;
import java.util.List;

public abstract class AOCProblem {
    public void run() throws IOException {
        System.out.println("=== Part 1 ===");
        System.out.println(">> Test Input #1" + ": " + part1(getTestInput(1)));
        for (int i = 1; i < getNumberOfTestInputs(); i++) {
            System.out.println(">> Test Input #" + (i+1) + ": " + part1(getTestInput(i)));
        }
        System.out.println(">> Main Input: " + part1(getInput()));

        System.out.println();

        System.out.println("=== Part 2 ===");
        System.out.println(">> Test Input #1" + ": " + part2(getTestInput(1)));
        for (int i = 1; i < getNumberOfTestInputs(); i++) {
            System.out.println(">> Test Input #" + (i+1) + ": " + part2(getTestInput(i)));
        }
        System.out.println(">> Main Input: " + part2(getInput()));
    }

    private List<String> getInput() throws IOException {
        return HelperElf.getAllLinesFromInputFile(getDay(), getYear());
    }

    private List<String> getTestInput(int numTestInput) throws IOException {
        if (numTestInput == 1) {
            return HelperElf.getAllLinesFromTestFile(getDay(), getYear());
        }
        return HelperElf.getAllLinesFromTestFile(getDay(), getYear(), numTestInput);
    }

    int getNumberOfTestInputs() {
        return 1;
    }

    abstract int getDay();
    abstract int getYear();
    abstract String part1(List<String> input);
    abstract String part2(List<String> input);
}
