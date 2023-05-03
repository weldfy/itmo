package markup;

import java.util.*;

public class Text implements Markup {
    private String text;
    public Text (String text) {
        this.text = text;
    }
    @Override
    public void toMarkdown (StringBuilder s) {
        s.append(text);
    }

    @Override
    public void toHtml (StringBuilder s) {
        s.append(text);
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