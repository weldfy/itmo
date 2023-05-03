package markup;

public interface CanBeToHtml {
    String htmlTag();
    void toHtml(StringBuilder s);
}
