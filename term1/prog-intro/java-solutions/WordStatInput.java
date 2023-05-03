import java.util.*;
import java.io.*;

public class WordStatInput {
    public static void main(String args[]) {
        try {
            Scanner in = new Scanner(new File(args[0]), "UTF-8");
            HashMap<String, Integer> dictionary = new HashMap<>();
            ArrayList <String> words = new ArrayList<>();
            while (in.hasNextLine()) {
                String s = in.nextLine();
                int cnt = 0;
                for (int i = 0; i <= s.length(); i++) {
                    char c = ' ';
                    if (i != s.length()) {
                        c = s.charAt(i);
                    }
                    if (Character.getType(c) == Character.DASH_PUNCTUATION | Character.isLetter(c) | c == '\'') {
                        cnt++;
                    }
                    else {
                        if (cnt == 0) {
                            continue;
                        }
                        String word = (s.substring(i - cnt, i)).toLowerCase();
                        //word = word.substring(0, 3);
                        Integer x = dictionary.get(word);
                        //x++;

                        if (x == null) {
                            x = 1;
                        }
                        else {
                            x++;
                        }

                        dictionary.put(word, x);
                        words.add(word);
                        cnt = 0;
                    }  
                }
            }
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "utf-8"));
                try {
          //          for (Map.Entry entry : dictionary.entrySet()) {
            //            System.out.println(entry.getKey() + " " + entry.getValue().toString());
              //      }
 
                    for (int i = 0; i < words.size(); i++) {
                        Integer x = dictionary.get(words.get(i));
                        if (x != 0) {                            
                            writer.write(words.get(i));
                            writer.write(" ");
                            writer.write(x.toString());
                            writer.write("\n");
                        }
                        x = 0;
                        dictionary.put(words.get(i), x);
                    }
                }
                finally {
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println("Input file not found: " + e.getMessage());
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
        }    
    }
}