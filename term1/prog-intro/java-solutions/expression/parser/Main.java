package expression.parser;

import expression.TripleExpression;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        TripleExpression expression = new ExpressionParser().parse(in.nextLine());
        int x = in.nextInt();
        int y = in.nextInt();
        int z = in.nextInt();
        System.out.println(expression.evaluate(x, y, z));
        System.out.println(expression.toString());
        System.out.println(expression.toMiniString());
    }
}
