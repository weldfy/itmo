package mnk;

public class Game {
    private final Player player1, player2;
    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
    public int play(GameBoard board, int nm1, int nm2) {
        int h = 1;
        while (!board.isEnd()) {
            int result = move(board, h, nm1, nm2);
            if (result != -1) {
                return result;
            }
            h = (h == 1 ? 2 : 1);
        }
        return 0;
    }
    private int move(GameBoard board, int h, int nm1, int nm2) {
        board.print();
        Board newBoard = board;
        Position position = (h == 1 ? player1 : player2).move(newBoard, (h == 1 ? nm1 : nm2));
        if (!board.isValid(position)) {
            return (h == 1 ? 2 : 1);
        }
        return board.set(position, h);
    }
}
