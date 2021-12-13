import java.util.*;


/**
 * Learnings:
 *      Integer overflow for score values
 *      Check your essentials (score map) before going rabbithole
 *
 */
public class SyntaxScoring extends AOCProblem {
    @Override
    int getDay() {
        return 10;
    }

    @Override
    int getYear() {
        return 2021;
    }

    static Map<String, Integer> pointMapPart1 = Map.of(")", 3, "]", 57, "}", 1197, ">", 25137);
    static Map<String, Integer> pointMapPart2 = Map.of(")", 1, "]", 2, "}", 3, ">", 4);
    static Map<String, String> openCloseMap = Map.of("(", ")", "{", "}", "<", ">", "[", "]");

    public static void main(String[] args) throws Exception {
        new SyntaxScoring().run();
    }

    String part2(List<String> input) {
        ArrayList<Long> scores = new ArrayList<>();
        for (String st : input) {
            String[] characterList = st.split("");
            boolean isCorrupted = false;
            Stack<String> characterQueue = new Stack<>();
            for (String c : characterList) {
                if (openCloseMap.containsKey(c)) {
                    characterQueue.push(c);
                } else {
                    String matchCharacter = characterQueue.pop();
                    if (!openCloseMap.get(matchCharacter).equals(c)) {
                        isCorrupted = true;
                        break;
                    }
                }
            }
            if (!isCorrupted) {
                long score = 0;
                while (characterQueue.size() != 0) {
                    String current = characterQueue.pop();
                    score = score * 5 + pointMapPart2.get(openCloseMap.get(current));
                }
                scores.add(score);
            }
        }
        Collections.sort(scores);
        return String.valueOf(scores.get(scores.size() / 2));
    }

    String part1(List<String> input) {
        int syntaxScore = 0;
        for (String st : input) {
            String[] characterList = st.split("");
            Deque<String> characterQueue = new ArrayDeque<>();
            for (String c : characterList) {
                if (openCloseMap.containsKey(c)) {
                    characterQueue.push(c);
                } else {
                    String matchCharacter = characterQueue.pop();
                    if (!openCloseMap.get(matchCharacter).equals(c)) {
                        syntaxScore += pointMapPart1.get(c);
                        break;
                    }
                }
            }
        }
        return String.valueOf(syntaxScore);
    }
}
