package App.Multicenter.Widget;


import App.Multicenter.Space.SearchedString;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

public class ImageWidget extends AbstractWidget {
    String nombreFoto;
    File img;

    public ImageWidget(File f) {
        nombreFoto = f.getName().split("\\.")[0];
        img = f;
    }

    public SearchedString<Widget> buscar(String cadena) {
        SearchedString<Widget> s = new SearchedString<>(this, nombreFoto, cadena);
        return s;
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
