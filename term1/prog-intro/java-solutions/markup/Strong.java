package markup;

import java.util.*;

public class Strong extends ListMarkup {
    public Strong (List <Markup> text) {
        super(text);
    }
    @Override
    public String markdownTag() {
        return "__";
    }
    @Override
    public String htmlTag() {
        return "strong";
    }
}