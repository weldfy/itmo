package markup;

import java.util.List;

public class UnorderedList extends ListOfLists {
    List<ListItem> text;
    public UnorderedList (List <ListItem>  text) {
        super(text);
    }
    @Override
    public String htmlTag() {
        return "ul";
    }
}
