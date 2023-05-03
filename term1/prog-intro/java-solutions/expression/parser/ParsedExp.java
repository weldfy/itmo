package expression.parser;

public class ParsedExp {
    private final String expression;
    private int pos;
    public ParsedExp(String stringExpression) {
        expression = stringExpression;
        pos = 0;
    }
    public boolean isUnary () {
        boolean fl = skip();
        if (!(expression.charAt(pos) == '-' || expression.charAt(pos) == 'c')) {
            if (fl) {
                pos--;
            }
        }
        return expression.charAt(pos) == '-' || expression.charAt(pos) == 'c';
    }
    public String getUnary() throws Exception {
        skip();
        if (expression.charAt(pos) == '-') {
            next(1);
            return "-";
        }
		// :NOTE: слишком много магических констант
        if (pos + 4 < expression.length() && expression.startsWith("count", pos)) {
            next(5);
            return "count";
        }
        throw new Exception("Incorrect expression");
    }
    public void next(int x) {
        pos += x;
    }
    private boolean skip() {
        boolean fl = false;
        while (pos < expression.length() && Character.isWhitespace(expression.charAt(pos))) {
            pos++;
            fl = true;
        }
        return fl;
    }
    public String getVariable() {
        skip();
        char c = expression.charAt(pos);
        next(1);
        return String.valueOf(c);
    }
    public int getConst(String sign) {
        skip();
        StringBuilder ans = new StringBuilder(sign);
        while (pos < expression.length() && Character.isDigit(expression.charAt(pos))) {
            ans.append(expression.charAt(pos));
            pos++;
        }
        return Integer.parseInt(String.valueOf(ans));
    }
    public String getSign() throws Exception {
        skip();
        char c = expression.charAt(pos);
		// :NOTE: сделать соответствие между строкой, которая парсится, и размером - и станет гораздо читабельнее
        if (c == '+') {
            next(1);
            return "+";
        }
        if (c == '-') {
            next(1);
            return "-";
        }
        if (c == '*') {
            next(1);
            return "*";
        }
        if (c == '/') {
            next(1);
            return "/";
        }
        if (pos + 2 < expression.length() && expression.startsWith("set", pos)) {
            next(3);
            return "set";
        }
        if (pos + 4 < expression.length() && expression.startsWith("clear", pos)) {
            next(5);
            return "clear";
        }
        throw new Exception("Incorrect expression");
    }
    private boolean isVariable(char c) {
        return c == 'x' || c == 'y' || c == 'z';
    }
    private boolean isSign(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == 's' || c == 'c';
    }
    public boolean isWhitespace() {
        return Character.isWhitespace(expression.charAt(pos));
    }
    public int checkNext() {
        skip();
		// :NOTE: если это "enum" - то уж лучше было делать настоящий enum, а не такую странную нумерацию 
        if (pos >= expression.length()) {
            return -1;
        }
        char c = expression.charAt(pos);
        if (c == '(') {
            return 2;
        }
        if (c == ')') {
            return 4;
        }
        if (Character.isDigit(c)) {
            return 0;
        }
        if (isVariable(c)) {
            return 1;
        }
        if (isSign(c)) {
            return 3;
        }
        return -1;
    }
}
