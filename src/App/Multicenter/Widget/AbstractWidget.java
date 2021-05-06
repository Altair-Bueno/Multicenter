package App.Multicenter.Widget;

import javax.swing.*;
import java.awt.*;

/**
 * The type Abstract widget.
 */
public abstract class AbstractWidget extends JInternalFrame implements Widget {
    String id;
    int layer;

    // Operaciones
    public boolean buscar(String cadena){
        // TODO AbstractWidget buscar
        return false;
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
