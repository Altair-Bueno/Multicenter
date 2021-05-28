package App.Multicenter.Widget;

import App.Multicenter.Space.SearchedString;

import javax.swing.*;
import java.awt.*;
import java.io.Closeable;
import java.io.Serializable;
import java.util.*;

/**
 * The type Abstract widget.
 */
public abstract class AbstractWidget implements Widget, Serializable, Closeable , Comparable<AbstractWidget> {

    public static final Dimension STANDARD_DIMENSION = new Dimension(100, 100);

    protected String id;
    protected int layer;
    protected Dimension dimension;
    protected float x;
    protected float y;

    // TODO cuildado que extend JINternalFrame da problemas

    // Operaciones

    public String getId() {
        return id;
    }

    public int getLayer() {
        return layer;
    }
/*
    public void setLayer(int capa) {
        layer = capa;
    }*/

    public void setId(String id) {
        this.id = id;
    }

    protected SearchedString<Widget> bestSearchedString(String frase, String ref, Widget w){
        SortedSet<SearchedString<Widget>> res = new TreeSet<>(Comparator.reverseOrder());
        Iterator<String> iter = Arrays.stream(frase.split("\\W+")).iterator();
        while(iter.hasNext()){
            res.add(new SearchedString<Widget>(w,frase,ref));
        }
        return res.first();
    }

    @Override
    public int compareTo(AbstractWidget o) {
        return this.layer - o.layer;
    }

    @Override
    public JInternalFrame getComponentView() {
        // Preparar el JInternalFrame
        return null;
    }
}
