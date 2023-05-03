package mnk;

public class GameBoard implements Board{
    static final char[] C = {'.', 'X', 'O'};
    private static int cnt;
    private final int m, n, k;
    private static char[][] board;
    public GameBoard (int m, int n, int k, boolean fl) {
        this.m = m;
        this.n = n;
        this.k = k;
        cnt = m * n;
        board = new char[m + 2][n + 2];
        for (int i = 0; i <= m + 1; i++) {
            for (int j = 0; j <= n + 1; j++) {
                if (fl && (i == j || i == n + 1 - j || j == m + 1 - i || n + 1 - j == m + 1 - i)) {
                    board[i][j] = 'E';
                } else {
                    board[i][j] = '.';
                }
            }
        }
    }
    public boolean isEnd() {
        return cnt == 0;
    }
    public int set(Position position, int nm) {
        cnt--;
        board[position.first()][position.second()] = C[nm];
        return isWinner(position, nm);
    }
    private int get(int x, int y, int a, int b, char c) {
        int ans = 0;
        while (board[x][y] == c) {
            ans++;
            x += a;
            y += b;
        }
        return ans;
    }
    private int isWinner (Position position, int nm) {
        if (get(position.first(), position.second(), 0, -1, C[nm])
                + get(position.first(), position.second(), 0, 1, C[nm]) - 1 >= k) {
            return nm;
        }
        if (get(position.first(), position.second(), -1, 0, C[nm])
                + get(position.first(), position.second(), 1, 0, C[nm]) - 1 >= k) {
            return nm;
        }
        if (get(position.first(), position.second(), -1, -1, C[nm])
                + get(position.first(), position.second(), 1, 1, C[nm]) - 1 >= k) {
            return nm;
        }
        if (get(position.first(), position.second(), -1, 1, C[nm])
                + get(position.first(), position.second(), 1, -1, C[nm]) - 1 >= k) {
            return nm;
        }
        return -1;
    }
    public void print () {
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
    @Override
    public boolean isValid(Position position) {
        return position.first() >= 1 && position.second() >= 1
                && position.first() <= m && position.second() <= n
                && board[position.first()][position.second()] == '.';
    }
    @Override
    public int count() {
        return cnt;
    }
    @Override
    public int rows() {
        return m;
    }
    @Override
    public int columns() {
        return n;
    }
}
