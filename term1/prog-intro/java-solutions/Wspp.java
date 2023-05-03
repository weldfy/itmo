import java.util.*;
import java.io.*;

public class Wspp {
    public static void main(String args[]) { 
        try { 
            Scan in = new Scan(new File(args[0]), "UTF-8");
            try {
                Map<String, IntList> dict = new LinkedHashMap<>();
                int counter = 0;
                while (in.checkNext()) {
                    String s = in.getNext();
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
                            counter++;
                            String word = (s.substring(i - cnt, i)).toLowerCase();
                            if (!dict.containsKey(word)) {
                                dict.put(word, new IntList());
                            }
                            dict.get(word).pushBack(counter);
                            cnt = 0;
                        }  
                    }
                }
                try {
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "utf-8"));
                    try { 
                        for (String key: dict.keySet()) {
                            writer.write(key + " " + dict.get(key).size() + " ");
                            for (int i = 0; i < dict.get(key).size() - 1; i++) {
                                writer.write(dict.get(key).get(i) + " ");
                            }
                            writer.write(dict.get(key).back() + "\n");
                        }   
                    }
                    finally {
                        writer.close();
                    }
                } catch (IOException e) {
                    System.out.println("Input file not found: " + e.getMessage());
                } 
            }finally {
                in.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
        }    
    }
}