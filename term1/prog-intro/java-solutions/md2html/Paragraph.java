package md2html;

import java.util.HashMap;
import java.util.Map;

public class Paragraph {
    //private static final String[] markupTagList = new String[]{"**", "__", "--", "*", "_", "`"};
    private static final Map<String, String> TAGS = new HashMap<>() {
        {
            put("**", "strong");
            put("__", "strong");
            put("--", "s");
            put("*", "em");
            put("_", "em");
            put("`", "code");
        }
    };
    private static final Map<Character, String> SPECIAL_HTML = new HashMap<>() {
        {
            put('<', "&lt;");
            put('>', "&gt;");
            put('&', "&amp;");
        }
    };
    private final StringBuilder paragraph;
    private int point;
    public Paragraph(String paragraph) {
        this.paragraph = new StringBuilder(paragraph);
    }
    public StringBuilder toHtml(final String tag) {
        final StringBuilder html = new StringBuilder();
        while (!isEmpty()) {
            String buff = get();
            if (buff.charAt(0) == '\\') {
                html.append(buff.charAt(1));
                next();
                continue;
            }
            final int tagSize = isTag(buff);
            if (tagSize > 0) {
                if (tagSize == 1) {
                    buff = buff.substring(0, 1);
                } else {
                    next();
                }
                if (buff.equals(tag)) {
                    return new StringBuilder().append("<").append(TAGS.get(tag))
                            .append(">").append(html).append("</").append(TAGS.get(tag)).append(">");
                }
                html.append(toHtml(buff));
            } else {
                if (buff.equals("![")) {
                    html.append(htmlImage());
                    continue;
                }
                final char c = buff.charAt(0);
                final String escaped = SPECIAL_HTML.get(c);
                html.append(escaped != null ? escaped : c);
            }
        }
        return new StringBuilder().append(tag).append(html);
    }

    private int isTag(final String tag) {
        for (String value : TAGS.keySet()) {
            if (tag.startsWith(value)) {
                return value.length();
            }
        }
        return 0;
    }

    private StringBuilder htmlImage() {
        return new StringBuilder().append("<img alt='")
                .append(getPart(']')).append("' src='").append(getPart(')')).append("'>");
    }
    private StringBuilder getPart(final char c) {
        final StringBuilder part = new StringBuilder();
        next();
        String buff = get();
        while (buff.charAt(0) != c) {
            part.append(buff.charAt(0));
            buff = get();
        }
        return part;
    }
    public String get() {
        point++;
        return String.valueOf(paragraph.charAt(point - 1)) +
                (point < paragraph.length() ? paragraph.charAt(point) : ' ');
    }
    public void next() {
        point++;
    }
    public boolean isEmpty() {
        return point >= paragraph.length();
    }
}
