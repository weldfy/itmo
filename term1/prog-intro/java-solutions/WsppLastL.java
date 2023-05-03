import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;

public class WsppLastL {
    public static void main(String[] args) {
        try {
            Scan in = new Scan(new File(args[0]), "UTF-8");
            try {
                Map<String, IntList> dict = new LinkedHashMap<>();
                Map<String, Integer> cntOfWords = new LinkedHashMap<>();
                int counter = 0;
                while (in.checkNextLine()) {
                    String s = in.getNextLine();
                    if (s == null) {
                        continue;
                    }
                    counter++;
                    int cnt = 0;
                    int wordCnt = 0;
                    for (int i = 0; i <= s.length(); i++) {
                        if (i != s.length() && isPartOfWord(s.charAt(i))) {
                            cnt++;
                        } else {
                            if (cnt == 0) {
                                continue;
                            }
                            wordCnt++;
                            String word = (s.substring(i - cnt, i)).toLowerCase();
                            IntList list = dict.get(word);
                            if (!dict.containsKey(word)) {
                                list = new IntList();
                                list.pushBack(wordCnt);
                                list.pushBack(counter);
                                dict.put(word, list);
                            }

                            if (list.back() == counter) {
                                list.change(list.size() - 2, wordCnt);
                            } else {
                                list.pushBack(wordCnt);
                                list.pushBack(counter);
                            }
                            cntOfWords.merge(word, 1, Integer::sum);
                            cnt = 0;
                        }
                    }
                }
                try {
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(
                                    new FileOutputStream(args[1]), StandardCharsets.UTF_8)
                    );
                    try {
                        for (Map.Entry<String, IntList> entry : dict.entrySet()) {
                            String key = entry.getKey();
                            IntList list = entry.getValue();
                            writer.write(key + " " + cntOfWords.get(key));
                            //System.err.print(key + " " + dict.get(key).size());
                            for (int i = 0; i < list.size(); i += 2) {
                                writer.write(" " + list.get(i));
                                //System.err.print(" " + dict.get(key).get(i));
                            }
                            writer.newLine();
                            //System.err.println();
                        }
                    } finally {
                        writer.close();
                    }
                } catch (IOException e) {
                    System.out.println("Input file not found: " + e.getMessage());
                }
            } finally {
                in.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
        }
    }

    private static boolean isPartOfWord(char c) {
        return Character.getType(c) == Character.DASH_PUNCTUATION || Character.isLetter(c) || c == '\'';
    }
}