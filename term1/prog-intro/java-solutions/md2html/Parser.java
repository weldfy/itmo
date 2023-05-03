package md2html;

import java.io.*;

public class Parser {
    BufferedReader reader; // :NOTE: acces modifs
    boolean end;
    public Parser(final BufferedReader reader) {
        this.reader = reader;
        end = false;
    }
    public StringBuilder toHtml() throws IOException {
        final StringBuilder html = new StringBuilder();
        while (!end) {
            String paragraph = getParagraph();
            if (!paragraph.isEmpty()) {
                final int level = checkHead(paragraph);
                paragraph = paragraph.substring(level);
                html.append(paragraphToHtml(paragraph, level > 1 ? "h" + (level - 1) : "p")).append(System.lineSeparator());
            }
        }
        return html;
    }
    private String getParagraph() throws IOException {
        final StringBuilder paragraph = new StringBuilder();
        while (true) {
            final String line = reader.readLine();
            if (line == null) {
                end = true;
                break;
            }
            if (line.isEmpty()) {
                break;
            }
            if (!paragraph.isEmpty()) {
                paragraph.append(System.lineSeparator());
            }
            paragraph.append(line);
        }
        return String.valueOf(paragraph);
    }
    private int checkHead(final String paragraph) {
        int level = 0;
        while (level < paragraph.length() && paragraph.charAt(level) == '#') {
            level++;
        }
        if (level == paragraph.length() || !Character.isWhitespace(paragraph.charAt(level)) || level == 0) {
            return 0;
        }
        return level + 1;
    }
    private StringBuilder paragraphToHtml(final String p, final String tag) {
        return new StringBuilder().append("<").append(tag).append(">")
                .append(new Paragraph(p).toHtml("")).append("</").append(tag).append(">");
    }
}