import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) {
        Scan in = new Scan(System.in);
        int n = in.getNextInt();
        int mx = 0;
        int sum = 0;
        final int timaxA = 2000000;
        int[] pref = new int[timaxA];
        int[] ans = new int[timaxA];
        for(int i = 0; i < n; i++) {
            int a = in.getNextInt();
            sum += a;
            mx = Math.max(mx, a);
            pref[sum] = sum;
        }
        int c = 0;
        for(int i = 0; i < timaxA; i++) {
            if(c > pref[i]) {
                pref[i] = c;
            }
            else {
                c = pref[i];
            }
        }

        for(int i = mx; i < timaxA; i++) {
            int anser = 0;
            c = 0;
            while(c < sum) {
                c = pref[c + i];
                anser++;
            }
            ans[i] = anser;
        }

        int q = in.getNextInt();
        for(int i = 0; i < q; i++) {
            int t = in.getNextInt();
            if(t < mx) {
                System.out.println("Impossible");
            } else {
                System.out.println(ans[t]);
            }
        }
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
