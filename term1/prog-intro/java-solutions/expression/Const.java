package expression;

public class Const implements Expression, TripleExpression, DoubleExpression{
    Number x;
    public Const (int x) {
        this.x = x;
    }
    public Const (double x) {
        this.x = x;
    }
    public String toString () {
        return String.valueOf(x);
    }
    @Override
    public int evaluate (int x) {
        return this.x.intValue();
    }
    @Override
    public int evaluate (int x, int y, int z) {
        return this.x.intValue();
    }
    @Override
    public double evaluate (double y) {
        return this.x.doubleValue();
    }
    public boolean equals (Object x) {
        return x != null && x.getClass() == this.getClass() && this.x.equals(((Const) x).x);
    }
    @Override
    public String toMiniString () {
        return String.valueOf(x);
    }
}
