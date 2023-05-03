import java.util.*;
import java.lang.*;

public class IntList {
    private int sz;
    private int stn;
    private int[] a;

    public IntList () {
        sz = 2;
        stn = 0;
        a = new int[2]; 
    }

    public void popBack() {
        if (stn != 0) {
            stn--;
        }
    }

    public int size () {
        return stn;
    }

    public int get(int pos) throws ArrayIndexOutOfBoundsException {
        if (pos < 0 || pos >= stn) {
            throw new ArrayIndexOutOfBoundsException("Illegal index");
        }
        return a[pos];
    }
    
    public void change(int pos, int x) throws ArrayIndexOutOfBoundsException {
        if (pos < 0 || pos > stn) {
            throw new ArrayIndexOutOfBoundsException("Illegal index");
        }
        a[pos] = x;
    }

    public void pushBack (int x) {
        if (sz == stn) {
            int nw[] = Arrays.copyOf(a, sz * 2);
            sz *= 2;
            a = nw;
        }
        a[stn++] = x;
    }

    public int back() throws ArrayIndexOutOfBoundsException {
        if (stn - 1 < 0) {
            throw new ArrayIndexOutOfBoundsException("Illegal index");
        }
        return a[stn - 1];
    }
}