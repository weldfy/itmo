import java.util.*;

public class ReverseOctDec {
    public static final int DEFAULTESIZE = 2;
    public static void main(String args[]) {
        Scan in = new Scan(System.in);
        int all[][] = new int[DEFAULTESIZE][];
        int sz = 0; 
        int stn = DEFAULTESIZE; 
        int mx = 0;
        while (in.checkNextLine()) {
            //System.err.println("LOL");
            String SS = in.getNextLine();
            if (SS == null) {
                continue;
            }
            //System.err.println("LOL");
            //System.err.println(SS);
            Scan s = new Scan(SS);
            int line[] = new int[DEFAULTESIZE];
            int szLine = 0;
            int stnLine = DEFAULTESIZE;
            while (s.checkNext()) {
                String num = s.getNext();
                //System.err.print(num);
                int notation = 10;
                char last = num.charAt(num.length() - 1); 
                if (Character.toLowerCase(last) == 'o') {
                    notation = 8;
                    num = num.substring(0, num.length() - 1);
                }
                int sign = 1;
                if (num.charAt(0) == '-') {
                    num = num.substring(1, num.length());
                    sign = -1;
                }
                int x = 0;
                for (int i = 0; i < num.length(); i++) {
                    char c = num.charAt(i);
                    x = x * notation + Character.digit(c, 10);  
                }
                x *= sign;
                if (szLine == stnLine) {
                    int nw[] = Arrays.copyOf(line, stnLine * 2);
                    stnLine *= 2;
                    line = nw;
                }
                line[szLine++] = x;
            }
            if (sz == stn) {
                int nw[][] = Arrays.copyOf(all, stn * 2);
                stn *= 2;
                all = nw;
            }
            all[sz] = Arrays.copyOf(line, szLine);
            sz++;
        }
        for (int i = sz - 1; i >= 0; i--) {
            for (int j = all[i].length - 1; j >= 0; j--) {
                System.out.print(all[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}