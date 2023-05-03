import java.util.*;
import java.io.*;

public class WordStatWordsPrefix {
    public static void main(String args[]) {
        try {
            Scanner in = new Scanner(new File(args[0]), "UTF-8");
            try {
                Map<String, Integer> dict = new TreeMap<>();
                // :NOTE: ArrayList
                ArrayList<String> words = new ArrayList<>();
                while (in.hasNextLine()) {
                    String s = in.nextLine();
                    int cnt = 0;
                    for (int i = 0; i <= s.length(); i++) {
                        char c = ' ';
                        if (i != s.length()) {
                            c = s.charAt(i);
                        }
                        if (Character.getType(c) == Character.DASH_PUNCTUATION || Character.isLetter(c) || c == '\'') {
                            cnt++;
                        } else if (cnt > 0) {
                            String word = s.substring(i - cnt, i).toLowerCase();
                            word = word.substring(0, Math.min(word.length(), 3));
                            dict.merge(word, 1, Integer::sum);
                            words.add(word);
                            cnt = 0;
                        }  
                    }
                }
                try {
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "utf-8"));
                    try {
                        for (String key: dict.keySet()) {
                            // :NOTE: "\n"
                            writer.write(key + " " + dict.get(key) + "\n");
                        }
                    } finally {
                        writer.close();
                    }
                } catch (IOException e) {
                    // :NOTE: Output file writing error
                    System.out.println("IOException: " + e.getMessage());
                }
            } finally {
                in.close();
            }  
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
        }     
    }
}
