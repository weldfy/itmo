package markup;

import java.util.List;

public class ListItem implements CanBeToHtml {
    List<Html> text;
    public ListItem (List <Html>  text) {
        this.text = text;
    }
    @Override
    public String htmlTag() {
        return "li";
    }
    @Override
    public void toHtml(StringBuilder s) {
        s.append("<").append(htmlTag()).append(">");
        for (Html html : text) {
            html.toHtml(s);
        }
        s.append("</").append(htmlTag()).append(">");
    }
}
