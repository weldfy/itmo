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
    public static void main(String[] args) {
        Scan in = new Scan(System.in);
        int n = in.getNextInt();
        int m = in.getNextInt();
        char[][] a = new char[n][m];
        int x = 0;
        int y = 0;
        for (int i = 0; i < n; i++) {
            String s = in.getNext();
            for (int j = 0; j < m; j++) {
                a[i][j] = s.charAt(j);
                if (a[i][j] == 'A') {
                    x = i;
                    y = j;
                }
            }
        }
        int[] up = new int[m];
        int[] down = new int[m];
        for (int j = 0; j < m; j++) {
            while (x > up[j] && a[x - up[j] - 1][j] == '.') {
                up[j]++;
            }
            while (x < n - down[j] - 1 && a[x + down[j] + 1][j] == '.') {
                down[j]++;
            }
        }
        int mx = 1;
        int a1 = y;
        int a2 = y;
        int a3 = x;
        int a4 = x;
        int u = 1000000007;
        int d = 1000000007;
        for (int l = y; l >= 0; l--) {
            if (a[x][l] != 'A' && a[x][l] != '.') {
                break;
            }
            u = Math.min(u, up[l]);
            d = Math.min(d, down[l]);
            int D = d;
            int U = u;
            for (int r = y; r < m; r++) {
                if (a[x][r] != 'A' && a[x][r] != '.') {
                    break;
                }
                U = Math.min(U, up[r]);
                D = Math.min(D, down[r]);
                if ((r - l + 1) * (U + D + 1) > mx) {
                    mx = (r - l + 1) * (U + D + 1);
                    a1 = l;
                    a2 = r;
                    a3 = x - U;
                    a4 = x + D;
                }
            }
        }
        for (int i = a3; i <= a4; i++) {
            for (int j = a1; j <= a2; j++) {
                if (a[i][j] != 'A') {
                    a[i][j] = 'a';
                }
            }
        }
        for (int i = 0; i < a3; i++) {
            char c = ' ';
            for (int j = 0; j < m; j++) {
                if (a[i][j] != '.' && a[i][j] != 'A' && a[i][j] != 'a') {
                    c = Character.toLowerCase(a[i][j]);
                }
                if (a[i][j] == 'A' || a[i][j] == 'a') {
                    c = ' ';
                }
                if (c != ' ' && a[i][j] == '.') {
                    a[i][j] = c;
                }
            }
            for (int j = m - 1; j >= 0; j--) {
                if (a[i][j] != '.' && a[i][j] != 'A' && a[i][j] != 'a') {
                    c = Character.toLowerCase(a[i][j]);
                }
                if (a[i][j] == 'A' || a[i][j] == 'a') {
                    c = ' ';
                }
                if (c != ' ' && a[i][j] == '.') {
                    a[i][j] = c;
                }
            }
        }
        for (int j = 0; j < m; j++)
        {
            char c = ' ';
            for (int i = 0; i < a3; i++) {
                if (a[i][j] != '.' && a[i][j] != 'A' && a[i][j] != 'a') {
                    c = Character.toLowerCase(a[i][j]);
                }
                if (a[i][j] == 'A' || a[i][j] == 'a') {
                    c = ' ';
                }
                if (c != ' ' && a[i][j] == '.') {
                    a[i][j] = c;
                }
            }
            for (int i = a3 - 1; i >= 0; i--) {
                if (a[i][j] != '.' && a[i][j] != 'A' && a[i][j] != 'a') {
                    c = Character.toLowerCase(a[i][j]);
                }
                if (a[i][j] == 'A' || a[i][j] == 'a') {
                    c = ' ';
                }
                if (c != ' ' && a[i][j] == '.') {
                    a[i][j] = c;
                }
            }
        }

        for (int i = a4 + 1; i < n; i++) {
            char c = ' ';
            for (int j = 0; j < m; j++) {
                if (a[i][j] != '.' && a[i][j] != 'A' && a[i][j] != 'a') {
                    c = Character.toLowerCase(a[i][j]);
                }
                if (a[i][j] == 'A' || a[i][j] == 'a') {
                    c = ' ';
                }
                if (c != ' ' && a[i][j] == '.') {
                    a[i][j] = c;
                }
            }
            for (int j = m - 1; j >= 0; j--) {
                if (a[i][j] != '.' && a[i][j] != 'A' && a[i][j] != 'a') {
                    c = Character.toLowerCase(a[i][j]);
                }
                if (a[i][j] == 'A' || a[i][j] == 'a') {
                    c = ' ';
                }
                if (c != ' ' && a[i][j] == '.') {
                    a[i][j] = c;
                }
            }
        }
        for (int j = 0; j < m; j++)
        {
            char c = ' ';
            for (int i = a4 + 1; i < n; i++) {
                if (a[i][j] != '.' && a[i][j] != 'A' && a[i][j] != 'a') {
                    c = Character.toLowerCase(a[i][j]);
                }
                if (a[i][j] == 'A' || a[i][j] == 'a') {
                    c = ' ';
                }
                if (c != ' ' && a[i][j] == '.') {
                    a[i][j] = c;
                }
            }
            for (int i = n - 1; i >= a4 + 1; i--) {
                if (a[i][j] != '.' && a[i][j] != 'A' && a[i][j] != 'a') {
                    c = Character.toLowerCase(a[i][j]);
                }
                if (a[i][j] == 'A' || a[i][j] == 'a') {
                    c = ' ';
                }
                if (c != ' ' && a[i][j] == '.') {
                    a[i][j] = c;
                }
            }
        }

        for (int i = a3; i <= a4; i++) {
            char c = ' ';
            for (int j = 0; j < a1; j++) {
                if (a[i][j] != '.' && a[i][j] != 'A' && a[i][j] != 'a') {
                    c = Character.toLowerCase(a[i][j]);
                }
                if (a[i][j] == 'A' || a[i][j] == 'a') {
                    c = ' ';
                }
                if (c != ' ' && a[i][j] == '.') {
                    a[i][j] = c;
                }
            }
            for (int j = a1 - 1; j >= 0; j--) {
                if (a[i][j] != '.' && a[i][j] != 'A' && a[i][j] != 'a') {
                    c = Character.toLowerCase(a[i][j]);
                }
                if (a[i][j] == 'A' || a[i][j] == 'a') {
                    c = ' ';
                }
                if (c != ' ' && a[i][j] == '.') {
                    a[i][j] = c;
                }
            }
        }
        for (int j = 0; j < a1; j++)
        {
            char c = ' ';
            for (int i = a3; i <= a4; i++) {
                if (a[i][j] != '.' && a[i][j] != 'A' && a[i][j] != 'a') {
                    c = Character.toLowerCase(a[i][j]);
                }
                if (a[i][j] == 'A' || a[i][j] == 'a') {
                    c = ' ';
                }
                if (c != ' ' && a[i][j] == '.') {
                    a[i][j] = c;
                }
            }
            for (int i = a4; i >= a3; i--) {
                if (a[i][j] != '.' && a[i][j] != 'A' && a[i][j] != 'a') {
                    c = Character.toLowerCase(a[i][j]);
                }
                if (a[i][j] == 'A' || a[i][j] == 'a') {
                    c = ' ';
                }
                if (c != ' ' && a[i][j] == '.') {
                    a[i][j] = c;
                }
            }
        }

        for (int i = a3; i <= a4; i++) {
            char c = ' ';
            for (int j = a2 + 1; j < m; j++) {
                if (a[i][j] != '.' && a[i][j] != 'A' && a[i][j] != 'a') {
                    c = Character.toLowerCase(a[i][j]);
                }
                if (a[i][j] == 'A' || a[i][j] == 'a') {
                    c = ' ';
                }
                if (c != ' ' && a[i][j] == '.') {
                    a[i][j] = c;
                }
            }
            for (int j = m - 1; j >= a2 + 1; j--) {
                if (a[i][j] != '.' && a[i][j] != 'A' && a[i][j] != 'a') {
                    c = Character.toLowerCase(a[i][j]);
                }
                if (a[i][j] == 'A' || a[i][j] == 'a') {
                    c = ' ';
                }
                if (c != ' ' && a[i][j] == '.') {
                    a[i][j] = c;
                }
            }
        }
        for (int j = a2 + 1; j < m; j++)
        {
            char c = ' ';
            for (int i = a3; i <= a4; i++) {
                if (a[i][j] != '.' && a[i][j] != 'A' && a[i][j] != 'a') {
                    c = Character.toLowerCase(a[i][j]);
                }
                if (a[i][j] == 'A' || a[i][j] == 'a') {
                    c = ' ';
                }
                if (c != ' ' && a[i][j] == '.') {
                    a[i][j] = c;
                }
            }
            for (int i = a4; i >= a3; i--) {
                if (a[i][j] != '.' && a[i][j] != 'A' && a[i][j] != 'a') {
                    c = Character.toLowerCase(a[i][j]);
                }
                if (a[i][j] == 'A' || a[i][j] == 'a') {
                    c = ' ';
                }
                if (c != ' ' && a[i][j] == '.') {
                    a[i][j] = c;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < m; j++) {
                s.append(a[i][j]);
            }
            System.out.println(s);
        }
    }
}
//2Aj - Ai = Ak