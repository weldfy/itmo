package expression;

public class Subtract extends Sign {
    public Subtract (Expression left, Expression right) {
        super(left, right);
    }
    @Override
    public char getSign() {
        return '-';
    }
    public String sign() {
        return "-";
    }
    @Override
    public Number operation(Number left, Number right) {
        return left.getClass() == Integer.class ?
                left.intValue() - right.intValue() : left.doubleValue() - right.doubleValue();
    }
    @Override
    public String getL() {
        return "sc";
    }
    @Override
    public String getR() {
        return "-+sc";
    }
}
