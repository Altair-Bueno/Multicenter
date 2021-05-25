package App.Multicenter.Widget;


import App.Multicenter.Space.SearchedString;

import java.util.SortedSet;
import java.util.TreeSet;

public class HyperlinkWidget extends AbstractWidget{
    String link;

    public HyperlinkWidget(String l){
        link = l;
    }

    public SortedSet<SearchedString<Widget>> buscar(String cadena) {
        SortedSet<SearchedString<Widget>> res = new TreeSet<>();
        res.add(new SearchedString<>(this, link, cadena));
        return res;
    }

}
