package mnk;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Read reader = new Read();
        System.out.println("HOW MANY PLAYERS?");
        int cnt = reader.readNumber();
        System.out.println("WHAT IS THE SIZE OF THE GAME BOARD?");
        System.out.println("ENTER M");
        int m = reader.readNumber();
        System.out.println("ENTER N");
        int n = reader.readNumber();
        System.out.println("ENTER K");
        int k = reader.readNumber();
        int[] points = new int[cnt + 1];
        for (int i = 1; i <= cnt - 1; i++) {
            for (int x = 1; x <= cnt; x++) {
                int y = (x + i - 1) % cnt + 1;
                System.out.println("PLAYER" + x + " AND PLAYER" + y + " START THE GAME");
                Game game = new Game(new HumanPlayer(), new HumanPlayer());
                int result = game.play(new GameBoard(m, n, k, true), x, y);
                if (result == 0) {
                    System.out.println("DRAW");
                    points[x]++;
                    points[y]++;
                }
                if (result == 1) {
                    System.out.println("PLAYER" + x + " WIN THIS GAME");
                    points[x] += 3;
                }
                if (result == 2) {
                    System.out.println("PLAYER" + y + " WIN THIS GAME");
                    points[y] += 3;
                }
            }
        }
        System.out.println("PLAYER   POINTS");
        int mx = 0;
        for (int i = 1; i <= cnt; i++) {
            System.out.println("  " + i + "        " + points[i]);
            mx = Math.max(mx, points[i]);
        }
        ArrayList<Integer> winners = new ArrayList<>();
        for (int i = 1; i <= cnt; i++) {
            if (points[i] == mx) {
                winners.add(i);
            }
        }
        if (winners.size() == 1) {
            System.out.println("PLAYER" + winners.get(0) + " IS WINNER");
        } else {
            System.out.print("PLAYER" + winners.get(0));
            for (int i = 1; i < winners.size() - 1; i++) {
                System.out.print(", PLAYER" + winners.get(i));
            }
            System.out.println(" AND PLAYER" + winners.get(winners.size() - 1) + " ARE WINNERS");
        }

    }
}