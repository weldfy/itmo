package markup;

public interface CanBeToMarkdown {
    String markdownTag();
    void toMarkdown(StringBuilder s);
}
