package mnk;

public class HumanPlayer implements Player {
    public HumanPlayer() {
    }
    @Override
    public Position move(Board board, int nm) {
        Read reader = new Read();
        Position position;
        while (true) {
            System.out.println("PLAYER" + nm + " MOVE");
            System.out.println("ENTER ROW");
            int x = reader.readNumber();
            System.out.println("ENTER COLUMN");
            int y = reader.readNumber();
            position = new Position(x, y);
            if (board.isValid(position)) {
                break;
            }
            System.out.println("THIS POSITION IS NOT VALID");
        }
        return position;
    }
}
