import java.util.Scanner;
import java.util.ArrayList;

public class ReverseSum {
    
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int all[][] = new int[2][];
        int sz = 0, stn = 2, mx = 0;
        while (in.hasNextLine()) {
            Scanner s = new Scanner(in.nextLine());
            //ArrayList<Integer> line = new ArrayList<>();
            int line[] = new int[2];
            int Sz = 0, Stn = 2;
            while (s.hasNextInt()) {
                int x = s.nextInt();
                if (Sz == Stn) {
                    int nw[] = new int[Stn * 2];
                    System.arraycopy(line, 0, nw, 0, Sz);
                    Stn *= 2;
                    line = nw;
                }
                line[Sz++] = x;
            }
            if (sz == stn) {
                int nw[][] = new int[stn * 2][];
                System.arraycopy(all, 0, nw, 0, sz);
                stn *= 2;
                all = nw;
            }
            all[sz] = new int[Sz];
            System.arraycopy(line, 0, all[sz], 0, Sz);
            mx = Math.max(mx, Sz);
            sz++;
        }
        int vert[] = new int[mx];
        int horiz[] = new int[sz];
        for (int i = 0; i < sz; i++) {
            for (int j = 0; j < all[i].length; j++) {
                vert[j] += all[i][j];
                horiz[i] += all[i][j];
            }
        }
        for (int i = 0; i < sz; i++) {
            for (int j = 0; j < all[i].length; j++) {
                System.out.print(horiz[i] + vert[j] - all[i][j]); 
                System.out.print(" ");
            }
            System.out.println(); 
        }
    }
}