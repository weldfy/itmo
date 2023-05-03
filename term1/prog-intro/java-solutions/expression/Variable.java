package expression;

public class Variable implements Expression, TripleExpression, DoubleExpression{
    String arg;
    public Variable (String arg) {
        this.arg = arg;
    }
    public String toString() {
        return arg;
    }
    @Override
    public int evaluate (int x) {
        return x;
    }
    @Override
    public int evaluate (int x, int y, int z) {
        if (arg.equals("x")) {
            return x;
        }
        if (arg.equals("y")) {
            return y;
        }
        return z;
    }
    @Override
    public double evaluate (double x) {
        return x;
    }
    @Override
    public boolean equals (Object x) {
        return x != null && x.getClass() == this.getClass() && arg.equals(x.toString());
    }
    public int hashCode () {
        return arg.hashCode();
    }
    @Override
    public String toMiniString () {
        return arg;
    }
}
