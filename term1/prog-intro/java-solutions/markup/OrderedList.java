package markup;

import java.util.List;

public class OrderedList extends ListOfLists {
    List<ListItem> text;
    public OrderedList (List <ListItem>  text) {
        super(text);
    }
    @Override
    public String htmlTag() {
        return "ol";
    }
}
