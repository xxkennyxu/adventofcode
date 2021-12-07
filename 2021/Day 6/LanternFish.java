import java.io.BufferedReader;
import java.util.Arrays;
import java.util.stream.LongStream;

public class LanternFish {
    // On 80 days:
    // 25% memory First approach: Day Simulator, time: d*n*m, space: n + 2m (parent list, + new kids + temporary list join)
    // 20% memory Second Approach: Spawn Amount per Fish Calculator; flattened list, time: n*m space: n*m (parent list + kids)
    //  5% memory Third Approach: Recursive, time: n*m, space: n+m (parent list + recursive function call stack?)
    // Run-time bottleneck ^^
    // Fourth approach: create a count of fishes as the input values range from 0-8 and simulate days by shifting array

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = HelperElf.openFile(6, 2021);

        long[] fish = new long[9];
        String st;
        while ((st = bufferedReader.readLine()) != null) {
            Arrays.stream(st.split(","))
                    .map(Integer::parseInt)
                    .forEach(number -> fish[number]++);
        }

        int numDays = 256;
        for (int i = 0; i < numDays; i++) {
            long parentCount = fish[0];

            // shift all fish by 1 day
            for (int j = 1; j < fish.length; j++) {
                fish[j-1] = fish[j];
            }
            fish[8] = parentCount; // today's parents gave birth to NEW fish
            fish[6] += parentCount;
        }
        System.out.println(LongStream.of(fish).sum());
    }

    private static int thirdApproach(int startTimer, int numDays) {
        final int daysLeft = numDays - (startTimer + 1);
        if (daysLeft < 0) {
            return 0;
        }
        return 1 + thirdApproach(6, daysLeft) + thirdApproach(8, daysLeft);
    }
}
