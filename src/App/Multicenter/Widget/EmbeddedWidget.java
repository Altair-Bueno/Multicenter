package App.Multicenter.Widget;

import App.Multicenter.Space.SearchedString;
import App.Multicenter.Widget.Data.WidgetData;
import kong.unirest.Unirest;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

public class EmbeddedWidget extends AbstractWidget {
    String URL;
    String nombre;

    public EmbeddedWidget(String url, String nombreRecurso) {
        URL = url;
        this.nombre = nombreRecurso;
    }

    public SearchedString<Widget> search(String cadena) {
        SortedSet<SearchedString<Widget>> res = new TreeSet<>();
        res.add(new SearchedString<>(this, nombre, cadena));

        // www.example.org/domain1/file.html
        // urlparts = {www, example, org/domain1/file.html}
        // subdomains = {org, domain1, file, html}

        // www.example.org
        // urlparts = {www, example, org}
        // subdomains = {org}

        String[] urlparts = URL.split("\\.");
        String[] subdomains = urlparts[urlparts.length - 1].split("/");

        for (int i = 0; i < urlparts.length - 1; i++) {
            res.add(new SearchedString<>(this, urlparts[i], cadena));
        }

        for (String subdomain : subdomains) {
            res.add(new SearchedString<>(this, subdomain, cadena));
        }
//TODO
        return null;
    }

    @Override
    public void setLayer(int capa) {

    }

    @Override
    public void toggleEditMode() {
    }

    @Override
    public void deleteWidget() {

    }

    @Override
    public void moveFilesToFolder(File folder) throws IOException {
    }

    @Override
    public WidgetData getWidgetsDataInstance() {
        return null;
    }


    @Override
    public void close() throws IOException {}

}
