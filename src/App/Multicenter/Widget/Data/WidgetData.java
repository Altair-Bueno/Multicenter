package App.Multicenter.Widget.Data;

import java.awt.*;
import java.io.Serializable;

public abstract class WidgetData implements Serializable {
    public String classname;

    public String id;
    public int layer;
    public Dimension dimension;
    public float x;
    public float y;
}
