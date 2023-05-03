public class Reverse {
    public static void main(String[] args) {
        Scan in = new Scan(System.in);
        String[] s = new String[1];
        int sz = 1, stn = 0;
        while(in.checkNextLine()) {
            String S = in.getNextLine();
            if (S == null) {
                continue;
            }
            S = " " + S;
            //System.err.println(S);
            if (stn == sz) {
                sz *= 2;
                String[] nw = new String[sz];
                System.arraycopy(s, 0, nw, 0, sz/2);
                s = nw;
            }
            s[stn++] = S;
        }
        for (int i = stn - 1; i >= 0; i--) {
            int cnt = 0;
            for (int j = s[i].length() - 1; j >= 0; j--) {
                cnt++;
                if (s[i].charAt(j) == ' ') {
                    if (cnt > 1) {
                        System.out.print(s[i].substring(j + 1, j + cnt) + " ");
                    }
                    cnt = 0;
                }
            }
            System.out.println();
        }
    }
}