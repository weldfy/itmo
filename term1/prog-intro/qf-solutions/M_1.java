import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            Map<Integer, Integer> mp = new HashMap<>();
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
                mp.put(a[i], 0);
            }
            int ans = 0;
            for (int j = n - 1; j >= 0; j--) {
                for (int i = 0; i < j; i++) {
                    int x = 2 * a[j] - a[i];
                    if (mp.get(x) != null) ans += mp.get(x);
                }
                int c = mp.get(a[j]);
                c++;
                mp.put(a[j], c);
            }
            System.out.println(ans);
            t--;
        }
    }
}
//2Aj - Ai = Ak