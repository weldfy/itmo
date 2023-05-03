package expression;

public abstract class Sign implements Expression, TripleExpression, DoubleExpression {
    Expression left, right;
    protected abstract String sign();
    protected abstract char getSign();
    protected abstract Number operation(Number left, Number right);
    protected abstract String getL();
    protected abstract String getR();
    public Sign (Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    public String toString () {
        return "(" + left.toString() + " " + sign() + " " + right.toString() + ')';
    }
    public int evaluate (int x) {
        Number answer = operation(this.left.evaluate(x), this.right.evaluate(x));
        return answer.intValue();
    }
    @Override
    public int evaluate(int x, int y, int z) {
        Number answer = operation(((TripleExpression) this.left).evaluate(x, y, z), ((TripleExpression) this.right).evaluate(x, y, z));
        return answer.intValue();
    }
    @Override
    public double evaluate (double x) {
        Number answer = operation(((DoubleExpression) this.left).evaluate(x), ((DoubleExpression) this.right).evaluate(x));
        return answer.doubleValue();
    }
    @Override
    public boolean equals(Object x) {
        return x != null && x.getClass() == this.getClass() && toString().equals(x.toString());
    }
    public int hashCode () {
        return toString().hashCode();
    }
    @Override
    public String toMiniString() {
        char l = left instanceof Sign ? ((Sign) left).getSign() : 'C';
        char r = right instanceof Sign ? ((Sign) right).getSign() : 'C';
        return (check(l, false) ? '(' + left.toMiniString() + ')' : left.toMiniString())
                + " " + sign() + " " + (check(r, true) ? '(' + right.toMiniString() + ')' : right.toMiniString());
    }
    public boolean check(char c, boolean nm) {
        final String parentheses = nm ? getR() : getL();
        for (int i = 0; i < parentheses.length(); i++) {
            if (parentheses.charAt(i) == c) {
                return true;
            }
        }
        return false;
    }
}