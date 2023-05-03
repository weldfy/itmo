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
        int x = -710 * 25000;
        for (int i = 0; i < n; i++) {
            System.out.println(x);
            x += 710;
        }
    }

}
//2Aj - Ai = Ak
//602