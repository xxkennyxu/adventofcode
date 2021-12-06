import java.io.BufferedReader;

public class DiveLocation {
    private static final String FORWARD = "forward";
    private static final String DOWN = "down";

    public static void main(String[] args) throws Exception {
        BufferedReader br = InputReader.open(2, 2021);

        String st;
        int horizontalLocation = 0;
        int verticalLocation = 0;
        while ((st = br.readLine()) != null) {
            final String[] diveCommand = st.split(" ");
            final String direction = diveCommand[0];
            final int directionValue = Integer.parseInt(diveCommand[1]);

            if (FORWARD.equals(direction)) {
                horizontalLocation += directionValue;
            } else if (DOWN.equals(direction)) {
                verticalLocation += directionValue;
            } else {
                verticalLocation = Math.max(0, verticalLocation - directionValue); // cap at 0, can't go above starting
            }
        }
        System.out.println(horizontalLocation * verticalLocation);
    }
}
