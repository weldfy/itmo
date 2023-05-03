package expression;

public class Negate extends Unary{
    public Negate (Expression expression) {
        super(expression);
    }
    public int operation(int x) {
        return -x;
    }
    public String getSign() {
        return "-";
    }
}
