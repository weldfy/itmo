package expression;

import java.util.BitSet;

public class Set extends Sign{
    public Set (Expression left, Expression right) {
        super(left, right);
    }
    @Override
    public char getSign() {
        return 's';
    }
    public String sign() {
        return "set";
    }
    @Override
    public Number operation(Number left, Number right) {
        return set(left.intValue(), right.intValue());
    }
    private int set(int value, int pos) {
//        if (pos < 0) {
//            pos = Math.abs(pos);
//            pos %= 32;
//            pos = -pos;
//        } else {
//            pos %= 32;
//        }
//        pos = (pos + 32)%32;
//        value = value | (1 << pos);
//        return value;
        value = value | (1 << pos);
        return value;
        /*
        BitSet bs = BitSet.valueOf(new long[]{value});
        bs.set(pos);
        int ans = 0;
        for (int i = 32; i >= 0; i--) {
            if (!bs.get(i)) {
                ans = ans * 2 + 1;
            } else {
                ans = ans * 2;
            }
        }
        ans -= 1;
        return ans;
        */
    }
    @Override
    public String getL() {
        return "";
    }
    @Override
    public String getR() {
        return "sc";
    }
}
