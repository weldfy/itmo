package mnk;

public interface Board {
    boolean isValid(Position position);
    int count();
    int rows();
    int columns();
}
