package App.Multicenter.Widget;

import App.Multicenter.Space.SearchedString;

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

    public SearchedString<Widget> buscar(String cadena) {
        SortedSet<SearchedString<Widget>> res = new TreeSet<>();
        res.add(new SearchedString<>(this, nombre, cadena));

        // www.example.org/domain1/file.html
        // urlparts = {www, example, org/domain1/file.html}
        // subdomains = {org, domain1, file, html}

        // www.example.org
        // urlparts = {www, example, org}
        // subdomains = {org}

        String[] urlparts = URL.split("\\.");
        String[] subdomains = urlparts[urlparts.length-1].split("/");

        for(int i = 0; i < urlparts.length - 1; i++){
            res.add(new SearchedString<>(this, urlparts[i], cadena));
        }

        for (String subdomain : subdomains) {
            res.add(new SearchedString<>(this, subdomain, cadena));
        }

        return res;
    }

    @Override
    public void toggleEditMode() {
    }

    @Override
    public void close() throws IOException {

    }
}
