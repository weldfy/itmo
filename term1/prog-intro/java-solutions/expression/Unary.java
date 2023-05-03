package expression;

public abstract class Unary implements Expression, TripleExpression{
    protected abstract int operation(int x);
    protected abstract String getSign();
    Expression expression;
    public Unary(Expression expression) {
        this.expression = expression;
    }
    public int evaluate(int x) {
        return operation(expression.evaluate(x));
    }
    @Override
    public int evaluate(int x, int y, int z) {
        return operation(((TripleExpression) expression).evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return getSign() + '(' + expression.toString() + ')';
    }
    public String toMiniString() {
        if (expression.getClass() == Const.class || expression.getClass() == Variable.class || expression instanceof Unary) {
            return getSign() + " " + expression.toMiniString();
        } else {
            return getSign() + '(' + expression.toMiniString() + ')';
        }

    }
}
