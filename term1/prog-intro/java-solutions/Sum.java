public class Sum {
    public static void main (String[] args) {
        int sum = 0;
        for (String arg : args) {
            StringBuilder strNum = new StringBuilder();
            for (int j = 0; j < arg.length(); j++) {
                char c = arg.charAt(j);
                if (Character.isWhitespace(c)) {
                    if (strNum.length() > 0) {
                        sum += Integer.parseInt(strNum.toString());
                        strNum = new StringBuilder();
                    }

                } else {
                    strNum.append(c);
                }
            }
            if (strNum.length() > 0) sum += Integer.parseInt(strNum.toString());
        }
        System.out.println(sum);		
    }
}