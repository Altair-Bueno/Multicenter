package App.Multicenter.Widget;

import App.Multicenter.Space.SearchedString;

import javax.swing.*;
import java.awt.*;
import java.io.Closeable;
import java.io.Serializable;
import java.util.SortedSet;

/**
 * The type Abstract widget.
 */
public abstract class AbstractWidget extends JInternalFrame implements Widget , Serializable, Closeable {
    String id;
    int layer;

    // Operaciones
    public SortedSet<SearchedString<Widget>> buscar(String cadena){
        // TODO AbstractWidget buscar
        return null;
    }
    public String getId(){
        // TODO AbstractWidget getId
       return id;
    }
    public int getLayer(){
        // TODO AbstractWidget getLayer
        return layer;
    }
    public void setLayer(int capa){
        // TODO AbstractWidget setLayer
        layer = capa;
    }
}
