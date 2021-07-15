package app.multicenter.widget;

import app.multicenter.space.SearchedString;
import app.multicenter.widget.data.WidgetData;

import java.io.File;
import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

public class EmbeddedWidget extends AbstractWidget {
    String URL;
    String nombre;

    /**
     * Crea un Widget del tipo Embedded, siendo este capaz de mostrar contenido
     * de cualquier tipo de página web dentro del proio widget.
     *
     * @param url
     * @param nombreRecurso
     */

    public EmbeddedWidget(String url, String nombreRecurso) {
        URL = url;
        this.nombre = nombreRecurso;
    }

    /**
     * Método search del Embedded. Comparará la cadena recibida por parámetro
     * con todas las partes separadas de la URL obtenida en el constructor.
     *
     * @param cadena La cadena a buscar.
     * @return
     */

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
    public void close() throws IOException {
    }

}
