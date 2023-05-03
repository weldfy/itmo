import java.util.*;
import java.io.*;
import java.lang.*;

class Scan {

    private Reader in;
    private final int sz = 15; // NOTE size
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

public class Main {

    public static void main(String[] args) {
        Scan in = new Scan(System.in);
        int n = in.getNextInt();
        int[] a = new int[n + 1];
        int[] pref = new int[n + 1];
        pref[0] = 0;
        for (int i = 1; i <= n; i++){
            a[i] = in.getNextInt();
            pref[i] = pref[i-1] + a[i];
        }
        int t = in.getNextInt();
        PairInt[] q = new PairInt[t];
        for (int i = 0; i < t; i++){
            q[i] = new PairInt();
            q[i].fi = in.getNextInt();
            q[i].se = i;
        }
        Arrays.sort(q);
        for (int i = 0; i < t; i++) {
            System.out.println(q[i].fi + " " + q[i].se);
        }
        int[] ans = new int[t];
        for (int i = 0; i < t; i++){
            if (i != 0 && q[i].fi == q[i-1].fi){
                ans[q[i].se] = ans[q[i-1].se];
            }
            int pos = 0;
            int sum = 0;
            ans[q[i].se] = 0;
            while (pos < pref.length){
                int nextPos = upperBound(pos, pref.length, sum + q[i].fi, pref);
                if (nextPos == pos){
                    ans[q[i].se] = -1;
                    break;
                }
                ans[q[i].se]++;
                pos = nextPos;
                nextPos--;
                sum = pref[nextPos];
            }
        }
        for (int i = 0; i < t; i++){
            if (ans[i] == -1){
                System.out.println("Impossible");
            }
            else {
                System.out.println(ans[i]);
            }

        }
    }
    public static int upperBound(int l, int r, int x, int[] pref) {
        while (l < r) {
            int m = (l + r) / 2;
            if (pref[m] <= x) {
                l = m + 1;
            }
            else {
                r = m;
            }
        }
        return l;
    }

}