package markup;

import java.util.*;

public class Strikeout extends ListMarkup{
    public Strikeout (List <Markup> text) {
        super(text);
    }
    @Override
    public String markdownTag() {
        return "~";
    }
    @Override
    public String htmlTag() {
        return "s";
    }
}