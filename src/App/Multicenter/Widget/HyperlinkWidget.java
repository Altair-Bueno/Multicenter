package App.Multicenter.Widget;


import App.Multicenter.Space.SearchedString;

import javax.swing.*;
import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

public class HyperlinkWidget extends AbstractWidget {
    String link;

    public HyperlinkWidget(String l) {
        link = l;
    }

    public SearchedString<Widget> buscar(String cadena) {
        
        return new SearchedString<>(this, link, cadena);
    }

    @Override
    public void setLayer(int capa) {

    }

    @Override
    public void toggleEditMode() {
    }

    @Override
    public JInternalFrame getComponentView() {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
