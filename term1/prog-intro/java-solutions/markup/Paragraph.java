package markup;

import java.util.*;

public class Paragraph implements Html, CanBeToMarkdown {
    List<Markup> text;
    public Paragraph (List <Markup> text) {
        this.text = text;
    }
    @Override
    public void toMarkdown (StringBuilder s) {
        for (int i = 0; i < text.size(); i++) {
            text.get(i).toMarkdown(s);
        }
    }
    @Override
    public void toHtml (StringBuilder s) {
        for (int i = 0; i < text.size(); i++) {
            text.get(i).toHtml(s);
        }
    }
    @Override
    public String htmlTag() {
        return "";
    }
    @Override
    public String markdownTag() {
        return "";
    }
}