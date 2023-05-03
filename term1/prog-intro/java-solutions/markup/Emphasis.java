package markup;

import java.util.*;

public class Emphasis extends ListMarkup{
    public Emphasis (List <Markup> text) {
        super(text);
    }
    @Override
    public String markdownTag() {
        return "*";
    }
    @Override
    public String htmlTag() {
        return "em";
    }
}