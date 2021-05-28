package App.Multicenter.Widget;


import App.Multicenter.Space.SearchedString;
import App.Multicenter.Widget.Data.WidgetData;

import java.io.File;
import java.io.IOException;

public class ImageWidget extends AbstractWidget {
    String nombreFoto;
    File img;

    public ImageWidget(File f) {
        nombreFoto = f.getName().split("\\.")[0];
        img = f;
    }

    public SearchedString<Widget> search(String cadena) {
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
    public void moveFilesToFolder(File folder) throws IOException {
    }

    @Override
    public WidgetData getWidgetsDataInstance() {
        return null;
    }


    @Override
    public void close() throws IOException {

    }
}
