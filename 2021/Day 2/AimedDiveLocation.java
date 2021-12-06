import java.io.BufferedReader;

public class AimedDiveLocation {
    private static final String FORWARD = "forward";
    private static final String DOWN = "down";

    public static void main(String[] args) throws Exception {
        BufferedReader br = InputReader.open(2, 2021);

        String st;
        int horizontalLocation = 0;
        int verticalLocation = 0;
        int aim = 0; // this value can be positive and negative
        while ((st = br.readLine()) != null) {
            final String[] diveCommand = st.split(" ");
            final String command = diveCommand[0];
            final int commandValue = Integer.parseInt(diveCommand[1]);

            if (FORWARD.equals(command)) {
                horizontalLocation += commandValue;
                verticalLocation = Math.max(0, verticalLocation + commandValue * aim); // cap at 0 again as aim can be negative going up
            } else if (DOWN.equals(command)) {
                aim += commandValue;
            } else {
                aim -= commandValue;
            }
        }
        System.out.println(horizontalLocation * verticalLocation);
    }
}
