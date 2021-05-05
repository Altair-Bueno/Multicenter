package App.Multicenter.Widget;

import java.awt.*;

/**
 * The type Abstract widget.
 */
public abstract class AbstractWidget implements Widget {
    int id;

    // Operaciones
    public boolean buscar(String cadena){return false;}
    public int getId(){
       return id;
    }
}
