package App.Multicenter.Widget;

import java.awt.*;

/**
 * The type Abstract widget.
 */
public abstract class abstractWidget implements Widget {
    Point coordenadas;
    Dimension size;
    int id;

    // Operaciones

    /**
     * Devuelve el objeto Point que almacena
     * las cooordenadas del widget en el
     * espacio personal.
     *
     * @return El objeto Point.
     */
    public Point getCoordenadas() {
        return coordenadas;
    }

    /**
     * Devuelve la dimensión del
     * widget almacenada en el
     * objeto Dimension "size".
     *
     * @return El objeto Dimension.
     */
    public Dimension getSize(){
        return size;
    }

    /**
     * Cambia las coordenadas del
     * widget.
     */
    public void setCoordenadas(){

    }

    /**
     * Cambia las dimensiones del
     * widget.
     */
    public void setSize(){

    }

    /**
     * Devuelve el identificador
     * del widget, que es un número
     * entero.
     *
     * @return El identificador.
     */
    public int getId(){
       return id;
    }
}
