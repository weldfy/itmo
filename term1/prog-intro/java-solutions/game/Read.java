package mnk;

import java.util.Scanner;

public class Read {
    public Read() {

    }
    int readNumber() {
        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                return Integer.parseInt(in.next());
            } catch (NumberFormatException e) {
                System.out.println("PLEASE ENTER CORRECT NUMBERS");
            }
        }
    }
}
