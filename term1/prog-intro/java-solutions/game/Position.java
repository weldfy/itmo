package mnk;

public class Position {
    private final int pos1;
    private final int pos2;
    public Position (int pos1, int pos2) {
        this.pos1 = pos1;
        this.pos2 = pos2;
    }
    public int first () {
        return pos1;
    }
    public int second () {
        return pos2;
    }
}
