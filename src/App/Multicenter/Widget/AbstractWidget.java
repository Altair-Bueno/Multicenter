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
        // TODO
        return false;
    }
    public String getId(){
        // TODO
       return id;
    }
    public int getLayer(){
        // TODO
        return layer;
    }
    public void setLayer(int capa){
        // TODO
        layer = capa;
    }
}
