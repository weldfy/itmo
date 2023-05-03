package expression;

public class Count extends Unary{
        public Count (Expression expression) {
            super(expression);
        }
        public int operation(int x) {
            return (int) countBinaryOnes(x);
        }
        public String getSign() {
            return "count";
        }
    private long countBinaryOnes(int number) {
        final char ONE = '1';
        return Integer.toBinaryString(number).chars().filter(item->item == ONE).count();
    }
}
