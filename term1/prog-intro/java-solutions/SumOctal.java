public class SumOctal {
    public static void main (String[] args) {
        int sum = 0;
        for (String arg : args) {
            int cnt = 0;
            for (int j = 0; j <= arg.length(); j++) {
                char c = ' ';
                if (j != arg.length()) {
                    c = arg.charAt(j);
                }
                if (Character.isWhitespace(c)) {
                    if (cnt == 0) {
                        continue;
                    }
                    char last = arg.charAt(j - 1);
                    int x = 10, pos = j;
                    if (Character.toLowerCase(last) == 'o') {
                        pos--;
                        cnt--;
                        x = 8;
                    }
                    if (x == 8) {
                        sum += Integer.parseUnsignedInt(arg.substring(pos - cnt, pos), x);
                    } else {
                        sum += Integer.parseInt(arg.substring(pos - cnt, pos), x);
                    }
                    cnt = 0;
                } else {
                    cnt++;
                }
            }
        }
        System.out.println(sum);
    }
}