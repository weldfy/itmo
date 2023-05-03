package mnk;

import java.util.Random;

public class RandomPlayer implements Player{
    private final Random random = new Random();
    public RandomPlayer() {

    }
    @Override
    public Position move(Board board, int nm) {
        System.out.println("RANDOM-PLAYER" + nm + " MOVE");
        int m = board.rows();
        int n = board.columns();
        int cnt = random.nextInt(board.count());
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                Position position = new Position(i, j);
                if (board.isValid(position)) {
                    if (cnt == 0) {
                        return position;
                    }
                    cnt--;
                }
            }
        }
        return new Position(0, 0);
    }
}
