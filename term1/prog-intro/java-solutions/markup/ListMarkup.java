package markup;

import java.util.List;

public abstract class ListMarkup implements Markup{
    List<Markup> text;
    public ListMarkup (List<Markup> text) {
        this.text = text;
    }
    @Override
    public void toMarkdown(StringBuilder s) {
        s.append(markdownTag());
        for (int i = 0; i < text.size(); i++) {
            text.get(i).toMarkdown(s);
        }
        s.append(markdownTag());
    }

    @Override
    public void toHtml(StringBuilder s) {
        s.append("<").append(htmlTag()).append(">");
        for (Markup markup : text) {
            markup.toHtml(s);
        }
        s.append("</").append(htmlTag()).append(">");
    }
}
