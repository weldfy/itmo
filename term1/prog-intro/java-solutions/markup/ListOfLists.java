package markup;

import java.util.*;

public abstract class ListOfLists implements Html {
    List<ListItem> text;
    public ListOfLists(List<ListItem> text) {
        this.text = text;
    }

    @Override
    public void toHtml(StringBuilder s) {
        s.append("<").append(htmlTag()).append(">");
        for (ListItem listItem : text) {
            listItem.toHtml(s);
        }
        s.append("</").append(htmlTag()).append(">");
    }
}
