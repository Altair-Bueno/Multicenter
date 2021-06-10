package app.multicenter.Widget.Data;

import java.awt.*;
import java.io.Serializable;

/**
 * Clase abstracta que contiene los datos comunes a
 * todos los Widgets. Solo debe utilizarse mediante polimorfismo
 *
 * @see app.multicenter.Widget.Widget#instanciateWidgetsFromData(WidgetData)
 */
public abstract class WidgetData implements Serializable {
    public String classname;

    public String id;
    public int layer;
    public Dimension dimension;
    public Point postion;
}