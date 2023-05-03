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

public class Main {
    static int[][] a = new int[101][101];
    static int[][] u = new int[101][101];
    static final int[][] m = {{-1, -1}, {-1, 1}, {-1, -1}, {-1, 1}, {-1, -1}};
    static final int[][] t1 = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    static final int[][][] t2 = {{{6, 7, 0}, {0, 1, 2}}, {{4, 5, 6}, {2, 3, 4}}};
    static final int[][] n = {{-1, 1}, {-1, -1}, {1, -1}, {-1, -1}, {-1, 1}, {-1, -1}, {1, -1}, {-1, -1}};
    static int w;
    static int h;

    public static void main(String[] args) {
        Scan in = new Scan(System.in);
        w = in.getNextInt();
        h = in.getNextInt();
        int ans = 0;
        int x = -1;
        int y = -1;
        for (int i = 0; i < h; i++) {
            String s = in.getNext();
            for (int j = 0; j < w; j++) {
                if (s.charAt(j) == 'X') {
                    ans++;
                    a[i][j] = 1;
                    if (x == -1 && y == -1) {
                        x = i;
                        y = j;
                    }

                }
            }
        }
        System.out.println(ans * 4 - 1);
        System.out.println(y + " " + x);
        dfs(x, y, -1, -1, 1, 1);
    }
    private static void dfs(int i, int j, int l, int r, int fl, int s) {
        int l1 = 0;
        int r1 = 0;
        if (fl == 0) {
            l1 = 1;
            r1 = 5;
        }
        else {
            l1 = 0;
            r1 = 4;
        }
        u[i][j] = 1;
        for (int k = l1; k < r1; k++) {
            for (int q = 0; q < t2[Math.max(0, l)][Math.max(0, r)].length; q++) {
                int x = t2[Math.max(l, 0)][Math.max(r, 0)][q];
                int i1 = i + t1[x][0];
                int j1 = j + t1[x][1];
                if (i1 >= 0 && j1 >= 0 && i1 < h && j1 < w && u[i1][j1] == 0 && a[i1][j1] == 1) {
                    dfs(i1, j1, l * n[x][0], r * n[x][1], fl, 0);
                }
            }
            fl = 1 - fl;
            l *= m[k][0];
            r *= m[k][1];
            if (k != 3 || s == 0) {
                System.out.print(j + Math.max(r, 0));
                System.out.print(" ");
                System.out.println(j + Math.max(l, 0));
            }
        }
    }
}
//2Aj - Ai = Ak