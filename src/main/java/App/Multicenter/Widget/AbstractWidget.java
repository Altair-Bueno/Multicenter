package app.multicenter.widget;

import app.multicenter.space.SearchedString;
import app.multicenter.widget.data.WidgetData;

import javax.swing.*;
import java.awt.*;
import java.io.Closeable;
import java.util.*;

/**
 * The type Abstract widget.
 */
public abstract class AbstractWidget extends JInternalFrame
        implements Widget, Closeable, Comparable<AbstractWidget> {

    // Constantes de valores por defecto
    public static final Dimension STANDARD_DIMENSION =
            new Dimension(200, 200);

    // Variables de clase
    protected String id;

    protected boolean edit = false;

    // Constructores
    protected AbstractWidget() {
    }

    protected AbstractWidget(WidgetData wd) {
        setSize(wd.dimension);
        setLocation(wd.postion);
        setLayer(wd.layer);
        id = wd.id;
    }

    public String getId() {
        return id;
    }


    // Operaciones
    protected SearchedString<Widget> bestSearchedString(String frase,
                                                        String ref,
                                                        Widget w) {
        SortedSet<SearchedString<Widget>> res =
                new TreeSet<>(
                        Comparator.reverseOrder()
                );
        Iterator<String> iter = Arrays
                .stream(frase.split("\\W+"))
                .iterator();
        while (iter.hasNext()) {
            res.add(new SearchedString<>(w, frase, ref));
        }
        return res.first();
    }

    @Override
    public int compareTo(AbstractWidget o) {
        return id.compareTo(o.id);
    }

    protected WidgetData getWidgetsDataInstance(WidgetData wd) {
        wd.dimension = getSize();
        wd.id = id;
        wd.postion = getLocation();
        wd.layer = getLayer();
        return wd;
    }
}
