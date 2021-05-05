package App.Multicenter.Widget;

import java.awt.*;

/**
 * The type Abstract widget.
 */
public abstract class AbstractWidget implements Widget {
    String id;
    int layer;

    // Operaciones
    public boolean buscar(String cadena){
        return false;
    }
    public String getId(){
       return id;
    }
    public int getLayer(){
        return layer;
    }
    public void setLayer(int capa){
        layer = capa;
    }
}
