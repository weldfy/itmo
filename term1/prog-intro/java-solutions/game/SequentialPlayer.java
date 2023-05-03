package mnk;

public class SequentialPlayer implements Player{
    public SequentialPlayer() {

    }
    @Override
    public Position move(Board board, int nm) {
        System.out.println("SEQUENTIAl-PLAYER" + nm + " MOVE");
        int m = board.rows();
        int n = board.columns();
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                Position position = new Position(i, j);
                if (board.isValid(position)) {
                    return position;
                }
            }
        }
        return new Position(0, 0);
    }
}
