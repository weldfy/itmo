import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int n = in.nextInt();
        int ans = 0;
        if ((n - b)%(b - a) > 0) ans++;
        ans += (n - b) / (b - a);
        ans *= 2;
        ans++;
        System.out.println(ans);
    }
}