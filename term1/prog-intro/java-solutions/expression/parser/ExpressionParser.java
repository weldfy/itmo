package expression.parser;

import expression.*;
import expression.Set;

import java.util.*;

// :NOTE: почему вообще не использован интерфейс Георгия Александровича? Этот самописный парсер выглядит крайне неочевидно
// Сначала надо парсить операции первого уровня и рекурсивно в них парсить операнды как элементы второго уровня
// Потом для второго уровня парсить операнды как элементы третьего
// И так далее
// То, что написано сейчас, не совсем похоже на то, что нужно

public class ExpressionParser implements TripleParser {
    private ParsedExp expression;
    private static final Map<String, Integer> LEVELS = new HashMap<>() {
        {
            put("*", 3);
            put("/", 3);
            put("-", 2);
            put("+", 2);
            put("set", 1);
            put("clear", 1);
        }
    };
    @Override
    public TripleExpression parse(String stringExpression) throws Exception {
        expression = new ParsedExp(stringExpression + ")");
        return parseLevel();
    }
    private TripleExpression parseLevel() throws Exception {
        ArrayList<TripleExpression> parsed = new ArrayList<>();
        ArrayList<String> sign = new ArrayList<>();
        while (true) {
            ArrayList<String> unary = new ArrayList<>();
            while (expression.isUnary()) {
                unary.add(expression.getUnary());
            }
            boolean whitespace = expression.isWhitespace();
			// :NOTE: неочевидное название (я понимаю, что flag, но всё же)
            int fl = expression.checkNext();
            TripleExpression newExp;
            if (fl == 0) {
                int x;
                if (!whitespace && unary.size() > 0 && "-".equals(unary.get(unary.size() - 1))) {
                    x = expression.getConst("-");
                    unary.remove(unary.size() - 1);
                } else {
                    x = expression.getConst("+");
                }
                newExp = new Const(x);
            } else {
                if (fl == 1) {
                    newExp = new Variable(expression.getVariable());
                } else {
                    if (fl == 2) {
                        expression.next(1);
                        newExp = parseLevel();
                    } else {
                        throw new Exception("Incorrect expression");
                    }
                }
            }
            for (int i = unary.size() - 1; i >= 0; i--) {
                assert newExp instanceof Expression;
                if ("-".equals(unary.get(i))) {
                    newExp = new Negate((Expression) newExp);
                } else {
                    newExp = new Count((Expression) newExp);
                }
            }
            parsed.add(newExp);
            fl = expression.checkNext();
            if (fl == 3) {
                sign.add(expression.getSign());
            } else {
                if (fl == 4) {
                    expression.next(1);
                    break;
                } else {
                    throw new Exception("Incorrect expression");
                }
            }
        }
        for (int i = 3; i >= 1; i--) {
            ArrayList<TripleExpression> newParsed = new ArrayList<>();
            ArrayList<String> newSign = new ArrayList<>();
            newParsed.add(parsed.get(0));
            for (int j = 1; j < parsed.size(); j++) {

                if (LEVELS.get(sign.get(j - 1)) == i) {
                    TripleExpression left;
                    left = (TripleExpression) get(newParsed.get(newParsed.size() - 1), parsed.get(j), sign.get(j - 1));
                    newParsed.remove(newParsed.size() - 1);
                    newParsed.add(left);
                } else {
                    newParsed.add(parsed.get(j));
                    newSign.add(sign.get(j - 1));
                }
            }
            parsed = newParsed;
            sign = newSign;
        }
        return parsed.get(0);
    }
    private Expression get (TripleExpression left, TripleExpression right, String sign) throws Exception {
        if (sign.equals("+")) {
            return new Add((Expression) left, (Expression) right);
        }
        if (sign.equals("-")) {
            return new Subtract((Expression) left, (Expression) right);
        }
        if (sign.equals("/")) {
            return new Divide((Expression) left, (Expression) right);
        }
        if (sign.equals("*")) {
            return new Multiply((Expression) left, (Expression) right);
        }
        if (sign.equals("set")) {
            return new Set((Expression) left, (Expression) right);
        }
        if (sign.equals("clear")) {
            return new Clear((Expression) left, (Expression) right);
        }
        throw new Exception("Incorrect expression");
    }
}