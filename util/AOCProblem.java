import java.io.IOException;
import java.util.List;

public abstract class AOCProblem {
    void run() throws IOException {
        System.out.println("=== Part 1 ===");
        System.out.println(">> Test Input: " + part1(getTestInput()));
        System.out.println(">> Main Input: " + part1(getInput()));
        System.out.println();
        System.out.println("=== Part 2 ===");
        System.out.println(">> Test Input: " + part2(getTestInput()));
        System.out.println(">> Main Input: " + part2(getInput()));
    }

    List<String> getInput() throws IOException {
        return HelperElf.getAllLinesFromInputFile(getDay(), getYear());
    }

    List<String> getTestInput() throws IOException {
        return HelperElf.getAllLinesFromTestFile(getDay(), getYear());
    }

    abstract int getDay();
    abstract int getYear();
    abstract String part1(List<String> input);
    abstract String part2(List<String> input);
}
