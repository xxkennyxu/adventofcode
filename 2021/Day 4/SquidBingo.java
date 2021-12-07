import java.io.BufferedReader;
import java.util.*;
import java.util.stream.Collectors;

public class SquidBingo {
    static class BingoCell {
        int x, y, number;
        boolean isMarked;

        public BingoCell(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.number = value;
            this.isMarked = false;
        }

        public String toString() {
            String spacer = "";
            if (number < 10) spacer = " ";

            if (isMarked) {
                return "[" + spacer + number + "]";
            }
            return " " + spacer + number + " ";
        }
    }

    static class BingoBoard {
        private int numMarkedCells;
        private int lineNum;

        BingoCell[][] board;
        HashMap<Integer, BingoCell> bingoCells;
        boolean hasWon;

        public BingoBoard() {
            this.board = new BingoCell[5][5];
            this.bingoCells = new HashMap<>();
            this.numMarkedCells = 0;
            this.lineNum = 0;
            this.hasWon = false;
        }

        public BingoBoard clone() {
            BingoBoard bingoBoard = new BingoBoard();
            for (int i = 0; i < this.board.length; i++) {
                for (int j = 0; j < this.board[i].length; j++) {
                    BingoCell bingoCell = new BingoCell(i, j, this.board[i][j].number);
                    bingoBoard.board[i][j] = bingoCell;
                    bingoBoard.bingoCells.put(this.board[i][j].number, bingoCell);
                }
            }
            return bingoBoard;
        }

        public int calculateWinningScore(int number) {
            if (bingoCells.containsKey(number)) {
                BingoCell bingoCell = bingoCells.get(number);
                bingoCell.isMarked = true;
                numMarkedCells++;

                if (this.numMarkedCells >= 5) {
                    // perform horizontal checks
                    int numMarked = 0;
                    for (int x = Math.min(0, bingoCell.x); x < board.length; x++) {
                        if (!board[x][bingoCell.y].isMarked) break; // if there's a single one missing, can't bingo
                        numMarked++;

                        if (numMarked == 5) {
                            this.hasWon = true;
                            return this.calculateScore(number);
                        }
                    }

                    // perform vertical checks
                    numMarked = 0;
                    for (int y = Math.min(0, bingoCell.y); y < board[bingoCell.x].length; y++) {
                        if (!board[bingoCell.x][y].isMarked) break; // if there's a single one missing, can't bingo
                        numMarked++;

                        if (numMarked == 5) {
                            this.hasWon = true;
                            return this.calculateScore(number);
                        }
                    }
                }
            }
            return -1; // no winner
        }

        public void addLine(List<Integer> numbers) {
            for (int i = 0; i < numbers.size(); i++) {
                board[lineNum][i] = new BingoCell(lineNum, i, numbers.get(i));
                bingoCells.put(numbers.get(i), board[lineNum][i]);
            }
            lineNum++;
        }

        private int calculateScore(int calledNumber) {
            int unmarkedNumberSum = 0;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    BingoCell cell = board[i][j];
                    if (!cell.isMarked) {
                        unmarkedNumberSum += cell.number;
                    }
                }
            }
            return unmarkedNumberSum * calledNumber;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    sb.append(board[i][j].toString());
                    if (j != board[i].length - 1) {
                        sb.append(" ");
                    }
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = HelperElf.openFile(4, 2021);

        List<Integer> bingoDraws = new ArrayList<>();
        ArrayList<BingoBoard> bingoBoards = new ArrayList<>();

        boolean newBoard = true;
        boolean hasInitializedDraws = false;
        String st;
        BingoBoard currentBoard = null;
        while ((st = bufferedReader.readLine()) != null) {
            if (!hasInitializedDraws) {
                bingoDraws = Arrays.stream(st.split(",")).map(Integer::parseInt).collect(Collectors.toList());
                hasInitializedDraws = true;
            } else if (st.isEmpty()) {
                if (!newBoard) {
                    bingoBoards.add(currentBoard);
                }
                newBoard = true;
            } else {
                if (newBoard) {
                    currentBoard = new BingoBoard();
                    newBoard = false;
                }
                currentBoard.addLine(Arrays.stream(st.split(" "))
                        .map(num -> {
                            if (num.isEmpty()) return -1;
                            return Integer.parseInt(num);
                        })
                        .filter(num -> num != -1)
                        .collect(Collectors.toList()));
            }
        }

        System.out.println("=== Part 1");
        partOne(bingoDraws, bingoBoards);
        System.out.println("=== Part 2");
        partTwo(bingoDraws, bingoBoards);
    }

    private static void partOne(List<Integer> bingoDraws, ArrayList<BingoBoard> inputBoards) {
        ArrayList<BingoBoard> bingoBoards = new ArrayList<>();
        Iterator<BingoBoard> iterator = inputBoards.iterator();
        while (iterator.hasNext()){
            bingoBoards.add(iterator.next().clone());
        }

        int score = -1;
        BingoBoard winningBoard = null;
        for (int draw : bingoDraws) {
            for (BingoBoard board : bingoBoards) {
                // if there are multiple winners, pick the one with the highest score
                final int newScore = board.calculateWinningScore(draw);
                if (newScore > score) {
                    winningBoard = board;
                    score = newScore;
                }
            }
            if (score != -1) break; // found winner, the highest score has been recorded
        }

        System.out.println(winningBoard);
        System.out.println(score);
    }

    private static void partTwo(List<Integer> bingoDraws, ArrayList<BingoBoard> inputBoards) {
        ArrayList<BingoBoard> bingoBoards = new ArrayList<>();
        Iterator<BingoBoard> iterator = inputBoards.iterator();
        while (iterator.hasNext()){
            bingoBoards.add(iterator.next().clone());
        }

        int numWinners = 0;
        BingoBoard lastBoard = null;
        int lastScore = -1;
        for (int draw : bingoDraws) {
            for (BingoBoard board : bingoBoards) {
                if (board.hasWon) continue;

                final int score = board.calculateWinningScore(draw);
                if (score != -1) {
                    lastBoard = board;
                    lastScore = score;
                    numWinners++;
                }
            }
            if (numWinners == bingoBoards.size()) break; // all boards have winners, last winner has been recorded
        }

        System.out.println(lastBoard);
        System.out.println(lastScore);
    }
}
