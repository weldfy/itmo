package md2html;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class Md2Html {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8))) {
            Parser parser = new Parser(reader);
            try (BufferedWriter writer = new BufferedWriter
                (new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
                writer.write(String.valueOf(parser.toHtml()));
            } catch (IOException e) {
                System.out.println("Output file writing error: " + e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
        }
    }
}
