import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static int mx = 0;
    public static int d = 0;
    public static String ANSWER = "YES";
    public static IntList ans = new IntList();
    public static IntList r = new IntList();
    public static void main(String[] args) {
        Scan in = new Scan(System.in);
        int m = in.getNextInt();
        int n = in.getNextInt();
        IntList[] v = new IntList[m + 1];
        for (int i = 0; i <= m; i++) {
            v[i] = new IntList();
        }
        for (int i = 0; i < m - 1; i++) {
            int a = in.getNextInt();
            int b = in.getNextInt();
            v[a].pushBack(b);
            v[b].pushBack(a);
        }
        boolean[] fl = new boolean[m + 1];
        int x = 0;
        for (int i = 0; i < n; i++) {
            int c = in.getNextInt();
            fl[c] = true;
            x = c;
        }
        if (n == 1) {
            System.out.println("YES");
            System.out.println(x);
            return;
        }
        d = x;
        getMax(x, -1, fl, 0, v);
        getWay(x, -1, v, d);
        x = ans.get((ans.size() - 1) / 2);
        d = (ans.size() - 1) / 2 + 1;
        getAnswer(x, -1, 0, v, d, fl);
        System.out.println(ANSWER);
        if (ANSWER == "YES") {
            System.out.println(x);
        }
    }
    public static void getAnswer(int x, int y, int s, IntList[] v, int d, boolean[] fl) {
        if (x == y) {
            return;
        }
        if (fl[x] && d != s) {
            ANSWER = "NO";
        }
        for (int i = 0; i < v[x].size(); i++) {
            if (v[x].get(i) == y) {
                continue;
            }
            s++;
            getAnswer(v[x].get(i), x, s, v, d, fl);
            s--;
        }
    }
    public static void getMax(int x, int y, boolean[] fl, int s, IntList[] v) {
        if (x == y) {
            return;
        }
        if (fl[x]) {
            if (mx < s)
            {
                mx = s;
                d = x;
            }
        }
        for (int i = 0; i < v[x].size(); i++) {
            if (v[x].get(i) == y) {
                continue;
            }
            s++;
            getMax(v[x].get(i), x, fl, s, v);
            s--;
        }
    }

    public static void getWay(int x, int y, IntList[] v, int d) {
        if (x == d) {
            for (int i = 0; i < r.size(); i++) {
                ans.pushBack(r.get(i));
            }
        }
        for (int i = 0; i < v[x].size(); i++) {
            if (v[x].get(i) == y) {
                continue;
            }
            r.pushBack(v[x].get(i));
            getWay(v[x].get(i), x, v, d);
            r.popBack();
        }
    }
}

class IntList {
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

class Scan {

    private Reader in;
    private final int sz = 1024; // NOTE size
    private String s; // NOTE block
    private int pos;
    private boolean finish;

    public Scan(InputStream inputStream) {
        in = new InputStreamReader(inputStream); // reader
        s = "";
        pos = 0;
        finish = false;
    }

    public Scan (File inputStream, String enc) throws FileNotFoundException {
        try {
            in = new InputStreamReader(new FileInputStream(inputStream), enc);
            s = "";
            pos = 0;
            finish = false;
        } catch (UnsupportedEncodingException e) {
            throw new FileNotFoundException ();
        }
    }

    public Scan(String inputStream) {
        in = new StringReader(inputStream);
        s = "";
        pos = 0;
        finish = false;
    }

    public void close() {
        try {
            in.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private String newBlock () {
        if (finish) {
            return "";
        }
        try {
            char[] nw = new char[sz];
            int charCount = in.read(nw);
            String s = new String(nw);
            charCount = Math.max(0, charCount);
            s = s.substring(0, charCount);
            pos = 0;
            if (s.length() < sz) {
                finish = true;
            }
            return s;
        } catch (IOException e) {
            finish = true;
            return "";
        }

    }
    public boolean checkNext() {
        boolean flag = false;
        while (!finish || s.length() != pos) {
            if (pos == s.length()) {
                s = newBlock();
            }
            while (pos < s.length() && Character.isWhitespace(s.charAt(pos))) {
                pos++;
            }
            if (pos != s.length()) {
                flag = true;
                break;
            }
        }

        return flag;
    }
    public String getNext() {
        StringBuilder ans = new StringBuilder();
        while (!finish || s.length() != pos) {
            if (pos == s.length()) {
                s = newBlock();
            }
            while (pos < s.length() && Character.isWhitespace(s.charAt(pos))) {
                pos++;
            }
            if (pos != s.length()) {
                break;
            }
        }
        while (!finish || s.length() != pos) {
            if (pos == s.length()) {
                s = newBlock();
            }
            while (pos < s.length() && !Character.isWhitespace(s.charAt(pos))) {
                ans.insert(ans.length(), s.charAt(pos));
                pos++;
            }
            if (pos != s.length()) {
                break;
            }
        }
        return ans.toString();
    }

    public int getNextInt() {
        return Integer.parseInt(getNext());
    }

    public boolean checkNextLine() {
        boolean flag = false;
        while (!finish || s.length() != pos) {
            if (pos == s.length()) {
                s = newBlock();
            }
            while (pos < s.length() && Character.isWhitespace(s.charAt(pos)) && s.charAt(pos) != '\n' && s.charAt(pos) != '\r') {
                pos++;
            }
            if (pos != s.length()) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    public String getNextLine() {
        StringBuilder ans = new StringBuilder();
        while (!finish || s.length() > pos) {
            if (pos == s.length()) {
                s = newBlock();
            }
            while (pos < s.length() && s.charAt(pos) != '\n' && s.charAt(pos) != '\r') {
                ans.insert(ans.length(), s.charAt(pos));
                pos++;
            }
            if (pos < s.length()) {
                pos++;
                break;
            }
        }
        return ans.toString();
    }
}

class PairInt implements Comparable<PairInt> {
    int fi;
    int se;
    public PairInt () {
        fi = 0;
        se = 0;
    }
    @Override
    public int compareTo(PairInt y) {
        if (fi > y.fi) {
            return 1;
        }
        if (fi < y.fi) {
            return -1;
        }
        if (se > y.se) {
            return 1;
        }
        if (se < y.se) {
            return -1;
        }
        return 0;
    }
}
